package com.kawa.service.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import com.kawa.service.dto.response.VendorResponseDTO;
import com.kawa.web.rest.TestUtil;
import java.util.Objects;
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

    @Test
    void testToString() {
        VendorResponseDTO vendorResponseDTO = new VendorResponseDTO();
        vendorResponseDTO.setId(1L);
        vendorResponseDTO.setToken("token");
        assertThat(vendorResponseDTO.toString()).hasToString("VendorResponseDTO{id=1, token='token'}");
    }

    @Test
    void testHashCode() {
        VendorResponseDTO vendorResponseDTO = new VendorResponseDTO();
        vendorResponseDTO.setId(1L);
        vendorResponseDTO.setToken("token");
        assertThat(vendorResponseDTO.hashCode()).hasSameHashCodeAs(Objects.hash(vendorResponseDTO.getId()));
    }

    @Test
    void testEqualSameObject() {
        VendorResponseDTO vendorResponseDTO = new VendorResponseDTO();
        vendorResponseDTO.setId(1L);
        vendorResponseDTO.setToken("token");
        VendorResponseDTO vendorResponseDTO2 = vendorResponseDTO;
        assertThat(vendorResponseDTO).isEqualTo(vendorResponseDTO2);
    }
}
