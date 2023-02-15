package com.kawa.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;

class VendorResponseMapperTest {

    private VendorResponseMapper vendorResponseMapper;

    @BeforeEach
    public void setUp() {
        vendorResponseMapper = new VendorResponseMapperImpl();
    }
}
