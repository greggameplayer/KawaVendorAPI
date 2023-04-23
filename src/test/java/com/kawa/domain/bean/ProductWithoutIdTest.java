package com.kawa.domain.bean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawa.web.rest.TestUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProductWithoutIdTest {

    private static final String JSON =
        "{\"createdAt\":\"2020-01-01T00:00:00.000Z\",\"name\":\"Product 1\",\"stock\":10,\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeAll
    static void setUp() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductWithoutId product = new ObjectMapper().readerFor(ProductWithoutId.class).readValue(JSON);
        assertEquals("Product 1", product.getName());
        assertEquals(10, product.getStock());
        assertEquals(10.0, product.getDetailsPrice());
        assertEquals("Description 1", product.getDetailsDescription());
        assertEquals("Color 1", product.getDetailsColor());
    }

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductWithoutId.class);
        ProductWithoutId product1 = new ProductWithoutId();
        product1.setCreatedAt(Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z")));
        product1.setName("name");
        product1.setStock(10);
        product1.setDetailsPrice(10.0);
        product1.setDetailsDescription("description");
        product1.setDetailsColor("color");
        ProductWithoutId product2 = new ProductWithoutId();
        product2.setCreatedAt(product1.getCreatedAt());
        product2.setName(product1.getName());
        product2.setStock(product1.getStock());
        product2.setDetailsPrice(product1.getDetailsPrice());
        product2.setDetailsDescription(product1.getDetailsDescription());
        product2.setDetailsColor(product1.getDetailsColor());
        assertEquals(product1, product2);
    }

    @Test
    void testHashCode() {
        Date createdAt = Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z"));
        ProductWithoutId product = new ProductWithoutId();
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(10);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        assertThat(product.hashCode()).hasSameHashCodeAs(product.getClass());
    }

    @Test
    void testToString() {
        Date createdAt = Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z"));
        ProductWithoutId product = new ProductWithoutId();
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(10);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        assertThat(product.toString())
            .hasToString(
                "ProductWithoutId{createdAt='2020-01-01T00:00:00.000Z', name='name', stock=10, detailsPrice=10.0, detailsDescription='description', detailsColor='color'}"
            );
    }

    @Test
    void testEqualsSameObject() {
        Date createdAt = Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z"));
        ProductWithoutId product = new ProductWithoutId();
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(10);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        ProductWithoutId product2 = product;
        assertThat(product).isEqualTo(product2);
    }
}
