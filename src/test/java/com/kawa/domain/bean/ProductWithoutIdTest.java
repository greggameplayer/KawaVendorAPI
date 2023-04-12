package com.kawa.domain.bean;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductWithoutIdTest {

    private static final String JSON =
        "{\"createdAt\":\"2020-01-01T00:00:00.000Z\",\"name\":\"Product 1\",\"stock\":10,\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}";

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductWithoutId product = new ObjectMapper().readerFor(ProductWithoutId.class).readValue(JSON);
        assertEquals("Product 1", product.getName());
        assertEquals(10, product.getStock());
        assertEquals(10.0, product.getDetailsPrice());
        assertEquals("Description 1", product.getDetailsDescription());
        assertEquals("Color 1", product.getDetailsColor());
    }
}
