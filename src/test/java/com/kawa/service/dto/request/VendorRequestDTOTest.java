package com.kawa.service.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class VendorRequestDTOTest {

    @Test
    void dtoEqualsVerifier() {
        VendorRequestDTO vendorRequestDTO1 = new VendorRequestDTO();
        vendorRequestDTO1.setName("name");
        vendorRequestDTO1.setPassword("password");
        vendorRequestDTO1.setUsername("username");
        vendorRequestDTO1.setEmail("email@gmail.com");
        VendorRequestDTO vendorRequestDTO2 = new VendorRequestDTO();
        assertThat(vendorRequestDTO1).isNotEqualTo(vendorRequestDTO2);
        vendorRequestDTO2.setName(vendorRequestDTO1.getName());
        vendorRequestDTO2.setPassword(vendorRequestDTO1.getPassword());
        vendorRequestDTO2.setUsername(vendorRequestDTO1.getUsername());
        vendorRequestDTO2.setEmail(vendorRequestDTO1.getEmail());
        assertThat(vendorRequestDTO1).isEqualTo(vendorRequestDTO2);
        vendorRequestDTO2.setName("name2");
        vendorRequestDTO2.setPassword("password2");
        vendorRequestDTO2.setUsername("username2");
        vendorRequestDTO2.setEmail("email2@gmail.com");
        assertThat(vendorRequestDTO1).isNotEqualTo(vendorRequestDTO2);
        vendorRequestDTO1.setName(null);
        vendorRequestDTO1.setPassword("password");
        vendorRequestDTO1.setUsername("username");
        vendorRequestDTO2.setEmail("email@gmail.com");
        assertThat(vendorRequestDTO1).isNotEqualTo(vendorRequestDTO2);
    }

    @Test
    void testToString() {
        VendorRequestDTO vendorRequestDTO = new VendorRequestDTO();
        vendorRequestDTO.setName("name");
        vendorRequestDTO.setPassword("password");
        vendorRequestDTO.setUsername("username");
        vendorRequestDTO.setEmail("email");
        assertThat(vendorRequestDTO.toString())
            .hasToString("VendorRequestDTO{name='name', email='email', username='username', password='password'}");
    }

    @Test
    void testHashCode() {
        VendorRequestDTO vendorRequestDTO = new VendorRequestDTO();
        vendorRequestDTO.setName("name");
        vendorRequestDTO.setPassword("password");
        vendorRequestDTO.setUsername("username");
        vendorRequestDTO.setEmail("email");
        assertThat(vendorRequestDTO.hashCode())
            .isEqualTo(
                Objects.hash(
                    vendorRequestDTO.getName(),
                    vendorRequestDTO.getEmail(),
                    vendorRequestDTO.getUsername(),
                    vendorRequestDTO.getPassword()
                )
            );
    }

    @Test
    void testEqualSameObject() {
        VendorRequestDTO vendorRequestDTO = new VendorRequestDTO();
        vendorRequestDTO.setName("name");
        vendorRequestDTO.setPassword("password");
        vendorRequestDTO.setUsername("username");
        vendorRequestDTO.setEmail("email");
        VendorRequestDTO vendorRequestDTO2 = vendorRequestDTO;
        assertThat(vendorRequestDTO).isEqualTo(vendorRequestDTO2);
    }
}
