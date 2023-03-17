package com.kawa.service;

import com.google.zxing.WriterException;
import com.kawa.service.dto.request.VendorRequestDTO;
import com.kawa.service.dto.response.VendorResponseDTO;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;

/**
 * Service Interface for managing {@link com.kawa.domain.Vendor}.
 */
public interface VendorService {
    /**
     * Save a vendor.
     *
     * @param vendorRequestDTO the entity to save.
     * @return the persisted entity.
     */
    VendorResponseDTO save(VendorRequestDTO vendorRequestDTO) throws IOException, WriterException, MessagingException;

    /**
     * Updates a vendor.
     *
     * @param vendorRequestDTO the entity to update.
     * @return the persisted entity.
     */
    VendorResponseDTO update(Long id, VendorRequestDTO vendorRequestDTO);

    /**
     * Get all the vendors.
     *
     * @return the list of entities.
     */
    List<VendorResponseDTO> findAll();

    /**
     * Get the "id" vendor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendorResponseDTO> findOne(Long id);

    /**
     * Delete the "id" vendor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
