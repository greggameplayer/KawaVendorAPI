package com.kawa.service.dto.request.mongo;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void dtoEqualsVerifier() {
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO1 = new ProductFindOneMongoRequestDTO();
        productFindOneMongoRequestDTO1.setFilterId("1");
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO2 = new ProductFindOneMongoRequestDTO();
        productFindOneMongoRequestDTO2.setFilterId(productFindOneMongoRequestDTO1.getFilterId());
        assertEquals(productFindOneMongoRequestDTO1, productFindOneMongoRequestDTO2);
        productFindOneMongoRequestDTO2.setFilterId("2");
        assertNotEquals(productFindOneMongoRequestDTO1, productFindOneMongoRequestDTO2);
        productFindOneMongoRequestDTO2.setFilterId(null);
        assertNotEquals(productFindOneMongoRequestDTO1, productFindOneMongoRequestDTO2);
    }

    @Test
    void testToString() {
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO = new ProductFindOneMongoRequestDTO();
        productFindOneMongoRequestDTO.setFilterId("1");

        assertEquals(
            "ProductFindOneMongoRequestDTO{dataSource='MainCluster', database='kawavendorapi', collection='products', filterId='1'}",
            productFindOneMongoRequestDTO.toString()
        );
    }

    @Test
    void testHashcode() {
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO1 = new ProductFindOneMongoRequestDTO();
        productFindOneMongoRequestDTO1.setFilterId("1");
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO2 = new ProductFindOneMongoRequestDTO();
        productFindOneMongoRequestDTO2.setFilterId(productFindOneMongoRequestDTO1.getFilterId());

        assertThat(productFindOneMongoRequestDTO1).hasSameHashCodeAs(productFindOneMongoRequestDTO2);
    }

    @Test
    void testEqualSameObject() {
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO = new ProductFindOneMongoRequestDTO();
        productFindOneMongoRequestDTO.setFilterId("1");
        ProductFindOneMongoRequestDTO productFindOneMongoRequestDTO1 = productFindOneMongoRequestDTO;

        assertThat(productFindOneMongoRequestDTO).isEqualTo(productFindOneMongoRequestDTO1);
    }
}
