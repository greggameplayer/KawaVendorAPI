package com.kawa.service.dto.request;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class VendorTokenValidityRequestDTOTest {

    @Test
    void dtoEqualsVerifier() {
        VendorTokenValidityRequestDTO vendorTokenValidityRequestDTO1 = new VendorTokenValidityRequestDTO();
        vendorTokenValidityRequestDTO1.setToken("token");
        VendorTokenValidityRequestDTO vendorTokenValidityRequestDTO2 = new VendorTokenValidityRequestDTO();
        assertThat(vendorTokenValidityRequestDTO1).isNotEqualTo(vendorTokenValidityRequestDTO2);
        vendorTokenValidityRequestDTO2.setToken(vendorTokenValidityRequestDTO1.getToken());
        assertThat(vendorTokenValidityRequestDTO1).isEqualTo(vendorTokenValidityRequestDTO2);
        vendorTokenValidityRequestDTO2.setToken("token2");
        assertThat(vendorTokenValidityRequestDTO1).isNotEqualTo(vendorTokenValidityRequestDTO2);
        vendorTokenValidityRequestDTO1.setToken(null);
        assertThat(vendorTokenValidityRequestDTO1.getToken()).isNull();
        assertThat(vendorTokenValidityRequestDTO2.getToken()).isNotNull();
    }
}
