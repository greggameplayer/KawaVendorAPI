package com.kawa.service.mapper.request;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.kawa.domain.Vendor;
import com.kawa.service.dto.request.VendorRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendorRequestMapperTest {

    private VendorRequestMapper vendorRequestMapper;

    @BeforeEach
    public void setUp() {
        vendorRequestMapper = new VendorRequestMapperImpl();
    }

    @Test
    void testMappingEntityToDto() {
        Vendor vendor = new Vendor();
        vendor.id(1L).email("email").name("name").password("password").token("token").username("username");

        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(vendor);

        assertThat(vendorRequestDTO).isNotNull();
        assertThat(vendorRequestDTO.getEmail()).isEqualTo(vendor.getEmail());
        assertThat(vendorRequestDTO.getName()).isEqualTo(vendor.getName());
        assertThat(vendorRequestDTO.getPassword()).isEqualTo(vendor.getPassword());
        assertThat(vendorRequestDTO.getUsername()).isEqualTo(vendor.getUsername());
    }

    @Test
    void testMappingDtoToEntity() {
        VendorRequestDTO vendorRequestDTO = new VendorRequestDTO();
        vendorRequestDTO.setEmail("email");
        vendorRequestDTO.setName("name");
        vendorRequestDTO.setPassword("password");
        vendorRequestDTO.setUsername("username");

        Vendor vendor = vendorRequestMapper.toEntity(vendorRequestDTO);

        assertThat(vendor).isNotNull();
        assertThat(vendor.getEmail()).isEqualTo(vendorRequestDTO.getEmail());
        assertThat(vendor.getName()).isEqualTo(vendorRequestDTO.getName());
        assertThat(vendor.getPassword()).isEqualTo(vendorRequestDTO.getPassword());
        assertThat(vendor.getUsername()).isEqualTo(vendorRequestDTO.getUsername());
    }
}
