package com.kawa.web.rest;

import com.google.zxing.WriterException;
import com.kawa.repository.VendorRepository;
import com.kawa.service.VendorService;
import com.kawa.service.dto.request.VendorRequestDTO;
import com.kawa.service.dto.request.VendorTokenValidityRequestDTO;
import com.kawa.service.dto.response.VendorResponseDTO;
import com.kawa.service.dto.response.VendorTokenValidityResponseDTO;
import com.kawa.web.rest.errors.BadRequestAlertException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kawa.domain.Vendor}.
 */
@RestController
@SecurityRequirement(name = "bearer")
@RequestMapping("/api")
public class VendorResource {

    private final Logger log = LoggerFactory.getLogger(VendorResource.class);

    private static final String ENTITY_NAME = "kawaVendorApiVendor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendorService vendorService;

    private final VendorRepository vendorRepository;

    public VendorResource(VendorService vendorService, VendorRepository vendorRepository) {
        this.vendorService = vendorService;
        this.vendorRepository = vendorRepository;
    }

    /**
     * {@code POST  /vendors} : Create a new vendor.
     *
     * @param vendorRequestDTO the vendorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendorDTO, or with status {@code 400 (Bad Request)} if the vendor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws MessagingException if the email could not be sent.
     * @throws IOException if the QR code could not be written to a file.
     * @throws WriterException if the QR code could not be generated.
     */
    @PostMapping("/vendors")
    public ResponseEntity<VendorResponseDTO> createVendor(@Valid @RequestBody VendorRequestDTO vendorRequestDTO)
        throws URISyntaxException, MessagingException, IOException, WriterException {
        log.debug("REST request to save Vendor : {}", vendorRequestDTO);
        VendorResponseDTO result = vendorService.save(vendorRequestDTO);
        return ResponseEntity
            .created(new URI("/api/vendors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vendors/:id} : Updates an existing vendor.
     *
     * @param id the id of the vendorDTO to save.
     * @param vendorRequestDTO the vendorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendorDTO,
     * or with status {@code 400 (Bad Request)} if the vendorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vendors/{id}")
    public ResponseEntity<VendorResponseDTO> updateVendor(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VendorRequestDTO vendorRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Vendor : {}, {}", id, vendorRequestDTO);

        if (!vendorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VendorResponseDTO result = vendorService.update(id, vendorRequestDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .body(result);
    }

    /**
     * {@code GET  /vendors} : get all the vendors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendors in body.
     */
    @GetMapping("/vendors")
    public List<VendorResponseDTO> getAllVendors() {
        log.debug("REST request to get all Vendors");
        return vendorService.findAll();
    }

    /**
     * {@code GET  /vendors/:id} : get the "id" vendor.
     *
     * @param id the id of the vendorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vendors/{id}")
    public ResponseEntity<VendorResponseDTO> getVendor(@PathVariable Long id) {
        log.debug("REST request to get Vendor : {}", id);
        Optional<VendorResponseDTO> vendorDTO = vendorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendorDTO);
    }

    /**
     * {@code DELETE  /vendors/:id} : delete the "id" vendor.
     *
     * @param id the id of the vendorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vendors/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        log.debug("REST request to delete Vendor : {}", id);
        vendorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /vendors/isValid} : Check if vendor token is valid.
     * @param vendorTokenValidityRequestDTO the vendorTokenValidityRequestDTO to check.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendorTokenValidityResponseDTO.
     * @throws MessagingException if the email could not be sent.
     * @throws IOException if the QR code could not be written to a file.
     * @throws WriterException if the QR code could not be generated.
     */
    @PostMapping("/vendors/isValid")
    public ResponseEntity<VendorTokenValidityResponseDTO> isVendorTokenValid(
        @Valid @RequestBody VendorTokenValidityRequestDTO vendorTokenValidityRequestDTO
    ) throws MessagingException, IOException, WriterException {
        log.debug("REST request to check if vendor token is valid : {}", vendorTokenValidityRequestDTO);
        VendorTokenValidityResponseDTO result = vendorService.isVendorTokenValid(vendorTokenValidityRequestDTO);
        return ResponseEntity.ok().body(result);
    }
}
