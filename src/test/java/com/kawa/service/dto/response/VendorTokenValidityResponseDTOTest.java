package com.kawa.service.dto.response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class VendorTokenValidityResponseDTOTest {

    @Test
    void dtoEqualsVerifier() {
        Date date = new Date();
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO1 = new VendorTokenValidityResponseDTO(true, true, date);
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO2 = new VendorTokenValidityResponseDTO(
            true,
            true,
            Date.from(Instant.now().plusSeconds(150))
        );
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO2.setExpiryDate(date);
        assertThat(vendorTokenValidityResponseDTO1).isEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO2.setValid(false);
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO1.setValid(false);
        assertThat(vendorTokenValidityResponseDTO1).isEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO2.setExpiryDate(Date.from(Instant.now().plusSeconds(10)));
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO1.setExpiryDate(null);
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
        vendorTokenValidityResponseDTO1.setExpiryDate(date);
        vendorTokenValidityResponseDTO2.setExpiryDate(date);
        vendorTokenValidityResponseDTO1.setExpired(false);
        assertThat(vendorTokenValidityResponseDTO1).isNotEqualTo(vendorTokenValidityResponseDTO2);
    }

    @Test
    void testToString() {
        Date date = new Date();
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO = new VendorTokenValidityResponseDTO(true, true, date);
        assertThat(vendorTokenValidityResponseDTO.toString())
            .hasToString("VendorTokenValidityResponseDTO{isValid=true, isExpired=true, expiryDate=" + date + "}");
    }

    @Test
    void testHashCode() {
        Date date = new Date();
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO = new VendorTokenValidityResponseDTO(true, true, date);
        assertThat(vendorTokenValidityResponseDTO.hashCode())
            .isEqualTo(
                Objects.hash(
                    vendorTokenValidityResponseDTO.getValid(),
                    vendorTokenValidityResponseDTO.getExpired(),
                    vendorTokenValidityResponseDTO.getExpiryDate()
                )
            );
    }

    @Test
    void testEqualSameObject() {
        Date date = new Date();
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO = new VendorTokenValidityResponseDTO(true, true, date);
        VendorTokenValidityResponseDTO vendorTokenValidityResponseDTO2 = vendorTokenValidityResponseDTO;
        assertThat(vendorTokenValidityResponseDTO).isEqualTo(vendorTokenValidityResponseDTO2);
    }
}
