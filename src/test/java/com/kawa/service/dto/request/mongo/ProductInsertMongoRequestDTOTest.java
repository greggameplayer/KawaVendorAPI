package com.kawa.service.dto.request.mongo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProductInsertMongoRequestDTOTest {

    private static final String JSON =
        "{\"dataSource\":\"MainCluster\",\"database\":\"kawavendorapi\",\"collection\":\"products\",\"document\":{\"name\":\"Product 1\",\"createdAt\":\"2021-01-01T00:00:00.000Z\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}}";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeAll
    static void setUp() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

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

    @Test
    void dtoEqualsVerifier() {
        Date date = new Date();

        ProductInsertMongoRequestDTO productInsertMongoRequestDTO1 = new ProductInsertMongoRequestDTO();
        productInsertMongoRequestDTO1.setDocumentName("Product 1");
        productInsertMongoRequestDTO1.setDocumentStock(1);
        productInsertMongoRequestDTO1.setDocumentDetailsPrice(10.0);
        productInsertMongoRequestDTO1.setDocumentDetailsDescription("Description 1");
        productInsertMongoRequestDTO1.setDocumentDetailsColor("Color 1");
        productInsertMongoRequestDTO1.setDocumentCreatedAt(date);

        ProductInsertMongoRequestDTO productInsertMongoRequestDTO2 = new ProductInsertMongoRequestDTO();
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentName(productInsertMongoRequestDTO1.getDocumentName());
        productInsertMongoRequestDTO2.setDocumentStock(productInsertMongoRequestDTO1.getDocumentStock());
        productInsertMongoRequestDTO2.setDocumentDetailsPrice(productInsertMongoRequestDTO1.getDocumentDetailsPrice());
        productInsertMongoRequestDTO2.setDocumentDetailsDescription(productInsertMongoRequestDTO1.getDocumentDetailsDescription());
        productInsertMongoRequestDTO2.setDocumentDetailsColor(productInsertMongoRequestDTO1.getDocumentDetailsColor());
        productInsertMongoRequestDTO2.setDocumentCreatedAt(productInsertMongoRequestDTO1.getDocumentCreatedAt());
        assertEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentName("Product 2");
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentName(productInsertMongoRequestDTO1.getDocumentName());
        productInsertMongoRequestDTO2.setDocumentStock(2);
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentStock(productInsertMongoRequestDTO1.getDocumentStock());
        productInsertMongoRequestDTO2.setDocumentDetailsPrice(20.0);
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentDetailsPrice(productInsertMongoRequestDTO1.getDocumentDetailsPrice());
        productInsertMongoRequestDTO2.setDocumentDetailsDescription("Description 2");
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentDetailsDescription(productInsertMongoRequestDTO1.getDocumentDetailsDescription());
        productInsertMongoRequestDTO2.setDocumentDetailsColor("Color 2");
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);

        productInsertMongoRequestDTO2.setDocumentDetailsColor(productInsertMongoRequestDTO1.getDocumentDetailsColor());
        productInsertMongoRequestDTO2.setDocumentCreatedAt(Date.from(Instant.now().plus(Duration.ofDays(1))));
        assertNotEquals(productInsertMongoRequestDTO1, productInsertMongoRequestDTO2);
    }

    @Test
    void testToString() {
        Date date = new Date();

        ProductInsertMongoRequestDTO productInsertMongoRequestDTO = new ProductInsertMongoRequestDTO();
        productInsertMongoRequestDTO.setDocumentName("Product 1");
        productInsertMongoRequestDTO.setDocumentStock(1);
        productInsertMongoRequestDTO.setDocumentDetailsPrice(10.0);
        productInsertMongoRequestDTO.setDocumentDetailsDescription("Description 1");
        productInsertMongoRequestDTO.setDocumentDetailsColor("Color 1");
        productInsertMongoRequestDTO.setDocumentCreatedAt(date);
        assertEquals(
            "ProductInsertMongoRequestDTO{dataSource='MainCluster', database='kawavendorapi', collection='products', documentCreatedAt='" +
            dateFormat.format(date) +
            "', documentName='Product 1', documentStock=1, documentDetailsPrice=10.0, documentDetailsDescription='Description 1', documentDetailsColor='Color 1'}",
            productInsertMongoRequestDTO.toString()
        );
    }

    @Test
    void testHashcode() {
        Date date = new Date();

        ProductInsertMongoRequestDTO productInsertMongoRequestDTO1 = new ProductInsertMongoRequestDTO();
        productInsertMongoRequestDTO1.setDocumentName("Product 1");
        productInsertMongoRequestDTO1.setDocumentStock(1);
        productInsertMongoRequestDTO1.setDocumentDetailsPrice(10.0);
        productInsertMongoRequestDTO1.setDocumentDetailsDescription("Description 1");
        productInsertMongoRequestDTO1.setDocumentDetailsColor("Color 1");
        productInsertMongoRequestDTO1.setDocumentCreatedAt(date);

        ProductInsertMongoRequestDTO productInsertMongoRequestDTO2 = new ProductInsertMongoRequestDTO();
        assertThat(productInsertMongoRequestDTO1.hashCode()).isNotEqualTo(productInsertMongoRequestDTO2.hashCode());

        productInsertMongoRequestDTO2.setDocumentName(productInsertMongoRequestDTO1.getDocumentName());
        productInsertMongoRequestDTO2.setDocumentStock(productInsertMongoRequestDTO1.getDocumentStock());
        productInsertMongoRequestDTO2.setDocumentDetailsPrice(productInsertMongoRequestDTO1.getDocumentDetailsPrice());
        productInsertMongoRequestDTO2.setDocumentDetailsDescription(productInsertMongoRequestDTO1.getDocumentDetailsDescription());
        productInsertMongoRequestDTO2.setDocumentDetailsColor(productInsertMongoRequestDTO1.getDocumentDetailsColor());
        productInsertMongoRequestDTO2.setDocumentCreatedAt(productInsertMongoRequestDTO1.getDocumentCreatedAt());
        assertThat(productInsertMongoRequestDTO1).hasSameHashCodeAs(productInsertMongoRequestDTO2);
    }

    @Test
    void testEqualSameObject() {
        Date date = new Date();

        ProductInsertMongoRequestDTO productInsertMongoRequestDTO = new ProductInsertMongoRequestDTO();
        productInsertMongoRequestDTO.setDocumentName("Product 1");
        productInsertMongoRequestDTO.setDocumentStock(1);
        productInsertMongoRequestDTO.setDocumentDetailsPrice(10.0);
        productInsertMongoRequestDTO.setDocumentDetailsDescription("Description 1");
        productInsertMongoRequestDTO.setDocumentDetailsColor("Color 1");
        productInsertMongoRequestDTO.setDocumentCreatedAt(date);
        ProductInsertMongoRequestDTO productInsertMongoRequestDTO1 = productInsertMongoRequestDTO;
        assertThat(productInsertMongoRequestDTO1).isEqualTo(productInsertMongoRequestDTO);
    }
}
