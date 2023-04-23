package com.kawa.service.dto.request;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductInsertRequestDTOTest {

    private static final String JSON =
        "{\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}";

    @Test
    void whenUsingJsonNode() throws Exception {
        ProductInsertRequestDTO productInsertRequestDTO = new ObjectMapper().readerFor(ProductInsertRequestDTO.class).readValue(JSON);
        assertEquals("Product 1", productInsertRequestDTO.getName());
        assertEquals(1, productInsertRequestDTO.getStock());
        assertEquals(10.0, productInsertRequestDTO.getDetailsPrice());
        assertEquals("Description 1", productInsertRequestDTO.getDetailsDescription());
        assertEquals("Color 1", productInsertRequestDTO.getDetailsColor());
    }
}
