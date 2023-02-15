package com.kawa.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.kawa.service.dto.response.VendorResponseDTO;
import com.kawa.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VendorResponseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorResponseDTO.class);
        VendorResponseDTO vendorResponseDTO1 = new VendorResponseDTO();
        vendorResponseDTO1.setId(1L);
        VendorResponseDTO vendorResponseDTO2 = new VendorResponseDTO();
        assertThat(vendorResponseDTO1).isNotEqualTo(vendorResponseDTO2);
        vendorResponseDTO2.setId(vendorResponseDTO1.getId());
        assertThat(vendorResponseDTO1).isEqualTo(vendorResponseDTO2);
        vendorResponseDTO2.setId(2L);
        assertThat(vendorResponseDTO1).isNotEqualTo(vendorResponseDTO2);
        vendorResponseDTO1.setId(null);
        assertThat(vendorResponseDTO1).isNotEqualTo(vendorResponseDTO2);
    }
}
