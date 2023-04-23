package com.kawa.service.dto.request;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductRequestDTOTest {

    private static final String JSON_WITH_ID = "{\"id\":\"5ff9f9b0e4b0b5b9b0b9b9b9\"}";
    private static final String JSON_WITHOUT_ID = "{\"id\":null}";

    @Test
    void whenUsingJsonNodeWithId() throws JsonProcessingException {
        ProductRequestDTO productRequestDTO = new ObjectMapper().readerFor(ProductRequestDTO.class).readValue(JSON_WITH_ID);
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", productRequestDTO.getId());
    }

    @Test
    void whenUsingJsonNodeWithoutId() throws JsonProcessingException {
        ProductRequestDTO productRequestDTO = new ObjectMapper().readerFor(ProductRequestDTO.class).readValue(JSON_WITHOUT_ID);
        assertNull(productRequestDTO.getId());
    }
}
