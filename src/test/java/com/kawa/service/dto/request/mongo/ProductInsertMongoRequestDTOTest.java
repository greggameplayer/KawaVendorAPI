package com.kawa.service.dto.request.mongo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductInsertMongoRequestDTOTest {

    private static final String JSON =
        "{\"dataSource\":\"MainCluster\",\"database\":\"kawavendorapi\",\"collection\":\"products\",\"document\":{\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductInsertMongoRequestDTO productInsertMongoRequestDTO = new ObjectMapper()
            .readerFor(ProductInsertMongoRequestDTO.class)
            .readValue(JSON);
        assertEquals("MainCluster", productInsertMongoRequestDTO.getDataSource());
        assertEquals("kawavendorapi", productInsertMongoRequestDTO.getDatabase());
        assertEquals("products", productInsertMongoRequestDTO.getCollection());
        assertEquals("Product 1", productInsertMongoRequestDTO.getDocumentName());
        assertEquals(1, productInsertMongoRequestDTO.getDocumentStock());
        assertEquals(10.0, productInsertMongoRequestDTO.getDocumentDetailsPrice());
        assertEquals("Description 1", productInsertMongoRequestDTO.getDocumentDetailsDescription());
        assertEquals("Color 1", productInsertMongoRequestDTO.getDocumentDetailsColor());
    }
}
