package com.kawa.service.impl;

import com.kawa.domain.Vendor;
import com.kawa.repository.VendorRepository;
import com.kawa.security.AuthoritiesConstants;
import com.kawa.security.jwt.TokenProvider;
import com.kawa.service.VendorService;
import com.kawa.service.dto.request.VendorRequestDTO;
import com.kawa.service.dto.response.VendorResponseDTO;
import com.kawa.service.mapper.VendorRequestMapper;
import com.kawa.service.mapper.VendorResponseMapper;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public VendorServiceImpl(
        VendorRepository vendorRepository,
        VendorResponseMapper vendorResponseMapper,
        VendorRequestMapper vendorRequestMapper,
        TokenProvider tokenProvider,
        PasswordEncoder passwordEncoder
    ) {
        this.vendorRepository = vendorRepository;
        this.vendorResponseMapper = vendorResponseMapper;
        this.vendorRequestMapper = vendorRequestMapper;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public VendorResponseDTO save(VendorRequestDTO vendorRequestDTO) {
        log.debug("Request to save Vendor : {}", vendorRequestDTO);
        // create a jwt token for the vendor
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            vendorRequestDTO.getUsername(),
            vendorRequestDTO.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))
        );
        Vendor vendor = vendorRequestMapper.toEntity(vendorRequestDTO);
        String jwt = tokenProvider.createToken(authentication, false);
        vendor.setToken(jwt);
        vendor.setPassword(passwordEncoder.encode(vendorRequestDTO.getPassword()));
        vendor = vendorRepository.save(vendor);
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
}
