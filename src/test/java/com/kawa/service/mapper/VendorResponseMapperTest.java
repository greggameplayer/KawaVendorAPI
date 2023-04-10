package com.kawa.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.kawa.domain.Vendor;
import com.kawa.service.dto.response.VendorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendorResponseMapperTest {

    private VendorResponseMapper vendorResponseMapper;

    @BeforeEach
    public void setUp() {
        vendorResponseMapper = new VendorResponseMapperImpl();
    }

    @Test
    void testMappingEntityToDto() {
        Vendor vendor = new Vendor();
        vendor.id(1L).email("email").name("name").password("password").token("token").username("username");

        VendorResponseDTO vendorResponseDTO = vendorResponseMapper.toDto(vendor);
        assertThat(vendorResponseDTO).isNotNull();
        assertThat(vendorResponseDTO.getId()).isEqualTo(vendor.getId());
        assertThat(vendorResponseDTO.getEmail()).isEqualTo(vendor.getEmail());
        assertThat(vendorResponseDTO.getName()).isEqualTo(vendor.getName());
        assertThat(vendorResponseDTO.getToken()).isEqualTo(vendor.getToken());
        assertThat(vendorResponseDTO.getUsername()).isEqualTo(vendor.getUsername());
    }

    @Test
    void testMappingDtoToEntity() {
        VendorResponseDTO vendorResponseDTO = new VendorResponseDTO();
        vendorResponseDTO.setId(1L);
        vendorResponseDTO.setEmail("email");
        vendorResponseDTO.setName("name");
        vendorResponseDTO.setToken("token");
        vendorResponseDTO.setUsername("username");

        Vendor vendor = vendorResponseMapper.toEntity(vendorResponseDTO);
        assertThat(vendor).isNotNull();
        assertThat(vendor.getId()).isEqualTo(vendorResponseDTO.getId());
        assertThat(vendor.getEmail()).isEqualTo(vendorResponseDTO.getEmail());
        assertThat(vendor.getName()).isEqualTo(vendorResponseDTO.getName());
        assertThat(vendor.getToken()).isEqualTo(vendorResponseDTO.getToken());
        assertThat(vendor.getUsername()).isEqualTo(vendorResponseDTO.getUsername());
    }
}
