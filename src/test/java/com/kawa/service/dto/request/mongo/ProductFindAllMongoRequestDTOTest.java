package com.kawa.service.dto.request.mongo;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void testToString() {
        ProductFindAllMongoRequestDTO productFindAllMongoRequestDTO = new ProductFindAllMongoRequestDTO();

        assertEquals(
            "ProductFindAllMongoRequestDTO{dataSource='MainCluster', database='kawavendorapi', collection='products'}",
            productFindAllMongoRequestDTO.toString()
        );
    }

    @Test
    void testHashcode() {
        ProductFindAllMongoRequestDTO productFindAllMongoRequestDTO1 = new ProductFindAllMongoRequestDTO();
        ProductFindAllMongoRequestDTO productFindAllMongoRequestDTO2 = new ProductFindAllMongoRequestDTO();

        assertThat(productFindAllMongoRequestDTO1).hasSameHashCodeAs(productFindAllMongoRequestDTO2);
    }

    @Test
    void testEqualSameObject() {
        ProductFindAllMongoRequestDTO productFindAllMongoRequestDTO = new ProductFindAllMongoRequestDTO();
        ProductFindAllMongoRequestDTO productFindAllMongoRequestDTO1 = productFindAllMongoRequestDTO;

        assertThat(productFindAllMongoRequestDTO).isEqualTo(productFindAllMongoRequestDTO1);
    }
}
