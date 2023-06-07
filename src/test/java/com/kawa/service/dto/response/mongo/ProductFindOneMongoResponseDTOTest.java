package com.kawa.service.dto.response.mongo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductFindOneMongoResponseDTOTest {

    private static final String JSON =
        "{\"document\":{\"_id\":\"5ff9f9b0e4b0b5b9b0b9b9b9\",\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductFindOneMongoResponseDTO productFindOneMongoResponseDTO = new ObjectMapper()
            .readerFor(ProductFindOneMongoResponseDTO.class)
            .readValue(JSON);

        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", productFindOneMongoResponseDTO.getDocumentId());
        assertEquals("Product 1", productFindOneMongoResponseDTO.getDocumentName());
        assertEquals(1, productFindOneMongoResponseDTO.getDocumentStock());
        assertEquals(10.0, productFindOneMongoResponseDTO.getDocumentDetailsPrice());
        assertEquals("Description 1", productFindOneMongoResponseDTO.getDocumentDetailsDescription());
        assertEquals("Color 1", productFindOneMongoResponseDTO.getDocumentDetailsColor());
    }
}
