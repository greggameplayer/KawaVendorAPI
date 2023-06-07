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

class ProductTest {

    private static final String JSON =
        "{\"_id\":\"1\",\"createdAt\":\"2020-01-01T00:00:00.000Z\",\"name\":\"Product 1\",\"stock\":10,\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeAll
    static void setUp() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        Product product = new ObjectMapper().readerFor(Product.class).readValue(JSON);
        assertEquals("1", product.getId());
        // assert createdAt date
        assertEquals("2020-01-01T00:00:00.000Z", dateFormat.format(product.getCreatedAt()));
        assertEquals("Product 1", product.getName());
        assertEquals(10, product.getStock());
        assertEquals(10.0, product.getDetailsPrice());
        assertEquals("Description 1", product.getDetailsDescription());
        assertEquals("Color 1", product.getDetailsColor());
    }

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId("1");
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertEquals(product1, product2);
        product2.setId("2");
        assertNotEquals(product1, product2);
        product1.setId(null);
        assertNotEquals(product1, product2);
    }

    @Test
    void testHashCode() {
        Date createdAt = Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z"));
        Product product = new Product();
        product.setId("1");
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(10);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        // test that the hashcode is the same as the class hashcode
        assertThat(product.hashCode()).hasSameHashCodeAs(product.getClass());
    }

    @Test
    void testToString() {
        Date createdAt = Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z"));
        Product product = new Product();
        product.setId("1");
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(10);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        assertThat(product.toString())
            .hasToString(
                "Product{id='1', createdAt='2020-01-01T00:00:00.000Z', name='name', stock=10, detailsPrice=10.0, detailsDescription='description', detailsColor='color'}"
            );
    }

    @Test
    void testEqualsSameObject() {
        Date createdAt = Date.from(java.time.Instant.parse("2020-01-01T00:00:00.000Z"));
        Product product = new Product();
        product.setId("1");
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(10);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        Product product2 = product;
        assertThat(product).isEqualTo(product2);
    }
}
