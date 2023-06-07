package com.kawa.service.dto.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawa.domain.bean.Product;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProductResponseDTOTest {

    private static final String JSON =
        "{\"products\":[{\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}]}";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeAll
    static void setUp() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        ProductResponseDTO productResponseDTO = new ObjectMapper().readerFor(ProductResponseDTO.class).readValue(JSON);
        assertEquals(1, productResponseDTO.getProducts().size());
        Product firstProduct = productResponseDTO.getProducts().get(0);
        assertEquals("Product 1", firstProduct.getName());
        assertEquals(1, firstProduct.getStock());
        assertEquals(10.0, firstProduct.getDetailsPrice());
        assertEquals("Description 1", firstProduct.getDetailsDescription());
        assertEquals("Color 1", firstProduct.getDetailsColor());
    }

    @Test
    void dtoEqualsVerifier() {
        ProductResponseDTO productResponseDTO1 = new ProductResponseDTO();
        Product product = new Product();
        product.setCreatedAt(Date.from(Instant.now()));
        product.setName("name");
        product.setStock(1);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        List<Product> products = new ArrayList<>();
        products.add(product);
        productResponseDTO1.setProducts(products);
        ProductResponseDTO productResponseDTO2 = new ProductResponseDTO();
        assertNotEquals(productResponseDTO1, productResponseDTO2);
        productResponseDTO2.setProducts(products);
        assertEquals(productResponseDTO1, productResponseDTO2);
    }

    @Test
    void testToString() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        Date createdAt = Date.from(Instant.now());
        Product product = new Product();
        product.setId("1");
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(1);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        List<Product> products = new ArrayList<>();
        products.add(product);
        productResponseDTO.setProducts(products);
        assertEquals(
            "ProductResponseDTO{products=[Product{id='1', createdAt='" +
            dateFormat.format(createdAt) +
            "', name='name', stock=1, detailsPrice=10.0, detailsDescription='description', detailsColor='color'}]}",
            productResponseDTO.toString()
        );
    }

    @Test
    void testHashcode() {
        ProductResponseDTO productResponseDTO1 = new ProductResponseDTO();
        Date createdAt = Date.from(Instant.now());
        Product product = new Product();
        product.setCreatedAt(createdAt);
        product.setName("name");
        product.setStock(1);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        List<Product> products = new ArrayList<>();
        products.add(product);
        productResponseDTO1.setProducts(products);
        ProductResponseDTO productResponseDTO2 = new ProductResponseDTO();
        assertNotEquals(productResponseDTO1.hashCode(), productResponseDTO2.hashCode());
        productResponseDTO2.setProducts(products);
        assertThat(productResponseDTO1).hasSameHashCodeAs(productResponseDTO2);
    }

    @Test
    void testEqualSameObject() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProducts(new ArrayList<>());
        ProductResponseDTO productResponseDTO2 = productResponseDTO;
        assertThat(productResponseDTO).isEqualTo(productResponseDTO2);
    }
}
