package com.kawa.service.impl;

import com.google.zxing.WriterException;
import com.kawa.domain.Vendor;
import com.kawa.repository.VendorRepository;
import com.kawa.security.AuthoritiesConstants;
import com.kawa.security.jwt.TokenProvider;
import com.kawa.service.EmailService;
import com.kawa.service.VendorService;
import com.kawa.service.dto.request.VendorRequestDTO;
import com.kawa.service.dto.request.VendorTokenValidityRequestDTO;
import com.kawa.service.dto.response.VendorResponseDTO;
import com.kawa.service.dto.response.VendorTokenValidityResponseDTO;
import com.kawa.service.mapper.request.VendorRequestMapper;
import com.kawa.service.mapper.response.VendorResponseMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Vendor}.
 */
@Service
@Transactional
public class VendorServiceImpl implements VendorService {

    private final Logger log = LoggerFactory.getLogger(VendorServiceImpl.class);

    private final VendorRepository vendorRepository;

    private final VendorResponseMapper vendorResponseMapper;

    private final VendorRequestMapper vendorRequestMapper;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Value("${email-service.username}")
    private String senderEmail;

    public VendorServiceImpl(
        VendorRepository vendorRepository,
        VendorResponseMapper vendorResponseMapper,
        VendorRequestMapper vendorRequestMapper,
        TokenProvider tokenProvider,
        PasswordEncoder passwordEncoder,
        EmailService emailService
    ) {
        this.vendorRepository = vendorRepository;
        this.vendorResponseMapper = vendorResponseMapper;
        this.vendorRequestMapper = vendorRequestMapper;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    private void generateQRCodeAndSendEmail(String token, String templateName, String emailSubject, Vendor vendor)
        throws IOException, WriterException, MessagingException {
        File qrCode = emailService.generateQRCode(token);
        String base64QrCode = DatatypeConverter.printBase64Binary(Files.readAllBytes(qrCode.toPath()));

        Map<String, Object> templateModel = new HashMap<>();

        templateModel.put("vendor", vendor.getName());
        templateModel.put("token", token);
        templateModel.put("qrCode", base64QrCode);

        Map<String, File> attachments = new HashMap<>();

        emailService.sendEmailFromTemplate(vendor.getEmail(), emailSubject, templateName, templateModel, true, true, attachments);
        Files.delete(qrCode.toPath());
    }

    @Override
    public VendorResponseDTO save(VendorRequestDTO vendorRequestDTO) throws IOException, WriterException, MessagingException {
        log.debug("Request to save Vendor : {}", vendorRequestDTO);
        // create a jwt token for the vendor
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            vendorRequestDTO.getUsername(),
            vendorRequestDTO.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))
        );
        Vendor vendor = vendorRequestMapper.toEntity(vendorRequestDTO);
        String jwt = tokenProvider.createToken(authentication, true);
        vendor.setToken(jwt);
        vendor.setPassword(passwordEncoder.encode(vendorRequestDTO.getPassword()));
        vendor = vendorRepository.save(vendor);

        generateQRCodeAndSendEmail(jwt, "new-vendor.ftlh", "Bienvenue chez Paye ton Kawa", vendor);

        return vendorResponseMapper.toDto(vendor);
    }

    @Override
    public VendorResponseDTO update(Long id, VendorRequestDTO vendorRequestDTO) {
        log.debug("Request to update Vendor : {}", vendorRequestDTO);
        return vendorRepository
            .findById(id)
            .map(existingVendor -> vendorRequestMapper.toEntity(vendorRequestDTO, id, existingVendor.getToken()))
            .map(existingVendor -> {
                existingVendor.setPassword(passwordEncoder.encode(vendorRequestDTO.getPassword()));
                return existingVendor;
            })
            .map(vendorRepository::save)
            .map(vendorResponseMapper::toDto)
            .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VendorResponseDTO> findAll() {
        log.debug("Request to get all Vendors");
        return vendorRepository.findAll().stream().map(vendorResponseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VendorResponseDTO> findOne(Long id) {
        log.debug("Request to get Vendor : {}", id);
        return vendorRepository.findById(id).map(vendorResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vendor : {}", id);
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorTokenValidityResponseDTO isVendorTokenValid(VendorTokenValidityRequestDTO vendorTokenValidityRequestDTO)
        throws MessagingException, IOException, WriterException {
        log.debug("Request to check if vendor token is valid : {}", vendorTokenValidityRequestDTO);

        boolean isValid = vendorRepository.existsByToken(vendorTokenValidityRequestDTO.getToken());
        boolean isExpired = false;
        Date expirationDate = null;

        if (isValid) {
            isExpired = !tokenProvider.validateToken(vendorTokenValidityRequestDTO.getToken());
            expirationDate = tokenProvider.getExpirationDate(vendorTokenValidityRequestDTO.getToken());
            if (isExpired) {
                Vendor vendor = vendorRepository.findByToken(vendorTokenValidityRequestDTO.getToken());

                // create a jwt token for the vendor
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    vendor.getUsername(),
                    vendor.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))
                );

                String jwt = tokenProvider.createToken(authentication, true);
                vendor.setToken(jwt);
                vendorRepository.save(vendor);

                generateQRCodeAndSendEmail(vendorTokenValidityRequestDTO.getToken(), "token-expired.ftlh", "Nouvel identifiant", vendor);
            }
        }

        return new VendorTokenValidityResponseDTO(isValid, isExpired, expirationDate);
    }
}
