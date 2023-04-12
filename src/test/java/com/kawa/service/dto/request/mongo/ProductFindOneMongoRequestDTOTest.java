package com.kawa.service.dto.request.mongo;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductFindOneMongoRequestDTOTest {

    private static final String JSON =
        "{\"dataSource\":\"MainCluster\",\"database\":\"kawavendorapi\",\"collection\":\"products\",\"filter\":{\"_id\":\"1\"}}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO = new ObjectMapper()
            .readerFor(ProductFindOneMongoRequestDTO.class)
            .readValue(JSON);
        assertEquals("MainCluster", productFindOneMongoRequestDTO.getDataSource());
        assertEquals("kawavendorapi", productFindOneMongoRequestDTO.getDatabase());
        assertEquals("products", productFindOneMongoRequestDTO.getCollection());
        assertEquals("1", productFindOneMongoRequestDTO.getFilterId());
    }
}
