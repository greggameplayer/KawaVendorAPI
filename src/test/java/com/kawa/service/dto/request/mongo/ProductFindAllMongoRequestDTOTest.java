package com.kawa.service.dto.request.mongo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductFindAllMongoRequestDTOTest {

    private static final String JSON = "{\"dataSource\":\"MainCluster\",\"database\":\"kawavendorapi\",\"collection\":\"products\"}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductFindAllMongoRequestDTO productFindAllMongoRequestDTO = new ObjectMapper()
            .readerFor(ProductFindAllMongoRequestDTO.class)
            .readValue(JSON);
        assertEquals("MainCluster", productFindAllMongoRequestDTO.getDataSource());
        assertEquals("kawavendorapi", productFindAllMongoRequestDTO.getDatabase());
        assertEquals("products", productFindAllMongoRequestDTO.getCollection());
    }
}
