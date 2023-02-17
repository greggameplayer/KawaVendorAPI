package com.kawa.service.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VendorRequestDTOTest {

    @Test
    void dtoEqualsVerifier() {
        VendorRequestDTO vendorRequestDTO1 = new VendorRequestDTO();
        vendorRequestDTO1.setName("name");
        vendorRequestDTO1.setPassword("password");
        vendorRequestDTO1.setUsername("username");
        VendorRequestDTO vendorRequestDTO2 = new VendorRequestDTO();
        assertThat(vendorRequestDTO1).isNotEqualTo(vendorRequestDTO2);
        vendorRequestDTO2.setName(vendorRequestDTO1.getName());
        vendorRequestDTO2.setPassword(vendorRequestDTO1.getPassword());
        vendorRequestDTO2.setUsername(vendorRequestDTO1.getUsername());
        assertThat(vendorRequestDTO1).isEqualTo(vendorRequestDTO2);
        vendorRequestDTO2.setName("name2");
        vendorRequestDTO2.setPassword("password2");
        vendorRequestDTO2.setUsername("username2");
        assertThat(vendorRequestDTO1).isNotEqualTo(vendorRequestDTO2);
        vendorRequestDTO1.setName(null);
        vendorRequestDTO1.setPassword("password");
        vendorRequestDTO1.setUsername("username");
        assertThat(vendorRequestDTO1).isNotEqualTo(vendorRequestDTO2);
    }
}
