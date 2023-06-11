package com.kawa.service.dto.response.mongo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawa.domain.bean.Product;
import org.junit.jupiter.api.Test;

class ProductFindAllMongoResponseDTOTest {

    private static final String JSON =
        "{\"documents\":[{\"_id\":\"5ff9f9b0e4b0b5b9b0b9b9b9\",\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}]}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductFindAllMongoResponseDTO productFindAllMongoResponseDTO = new ObjectMapper()
            .readerFor(ProductFindAllMongoResponseDTO.class)
            .readValue(JSON);

        assertEquals(1, productFindAllMongoResponseDTO.getDocuments().size());
        Product firstProduct = productFindAllMongoResponseDTO.getDocuments().get(0);
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", firstProduct.getId());
        assertEquals("Product 1", firstProduct.getName());
        assertEquals(1, firstProduct.getStock());
        assertEquals(10.0, firstProduct.getDetailsPrice());
        assertEquals("Description 1", firstProduct.getDetailsDescription());
        assertEquals("Color 1", firstProduct.getDetailsColor());
    }
}
