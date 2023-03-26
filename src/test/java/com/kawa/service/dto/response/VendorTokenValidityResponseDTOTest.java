package com.kawa.service.dto.response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Date;
import org.junit.jupiter.api.Test;

class VendorTokenValidityResponseDTOTest {

    @Test
    void dtoEqualsVerifier() {
        Date date = new Date();
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO1 = new VendorTokenValidityResponseDTO(true, true, date);
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO2 = new VendorTokenValidityResponseDTO(true, true, new Date());
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO2.setExpiryDate(date);
        assertThat(vendorTokenValidityResponseDTO1).isEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO2.setValid(false);
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO1.setValid(false);
        assertThat(vendorTokenValidityResponseDTO1).isEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO2.setExpiryDate(new Date());
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO1.setExpiryDate(null);
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO1.setExpiryDate(date);
        vendorTokenValidityResponseDTO2.setExpiryDate(date);
        vendorTokenValidityResponseDTO1.setExpired(false);
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
    }
}