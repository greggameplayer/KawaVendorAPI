package com.kawa.service.dto.response;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawa.domain.bean.Product;
import org.junit.jupiter.api.Test;

class ProductResponseDTOTest {

    private static final String JSON =
        "{\"products\":[{\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}]}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductResponseDTO productResponseDTO = new ObjectMapper().readerFor(ProductResponseDTO.class).readValue(JSON);
        assertEquals(1, productResponseDTO.getProducts().size());
        Product firstProduct = productResponseDTO.getProducts().get(0);
        assertEquals("Product 1", firstProduct.getName());
        assertEquals(1, firstProduct.getStock());
        assertEquals(10.0, firstProduct.getDetailsPrice());
        assertEquals("Description 1", firstProduct.getDetailsDescription());
        assertEquals("Color 1", firstProduct.getDetailsColor());
    }
}
