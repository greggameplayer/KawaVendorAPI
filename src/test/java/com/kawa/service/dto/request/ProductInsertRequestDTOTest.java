package com.kawa.service.dto.request;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProductInsertRequestDTOTest {

    private static final String JSON =
        "{\"name\":\"Product 1\",\"stock\":\"1\",\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}";

    @Test
    void whenUsingJsonNode() throws Exception {
        ProductInsertRequestDTO productInsertRequestDTO = new ObjectMapper().readerFor(ProductInsertRequestDTO.class).readValue(JSON);
        assertEquals("Product 1", productInsertRequestDTO.getName());
        assertEquals(1, productInsertRequestDTO.getStock());
        assertEquals(10.0, productInsertRequestDTO.getDetailsPrice());
        assertEquals("Description 1", productInsertRequestDTO.getDetailsDescription());
        assertEquals("Color 1", productInsertRequestDTO.getDetailsColor());
    }

    @Test
    void dtoEqualsVerifier() {
        ProductInsertRequestDTO productInsertRequestDTO1 = new ProductInsertRequestDTO();
        productInsertRequestDTO1.setName("name");
        productInsertRequestDTO1.setStock(1);
        productInsertRequestDTO1.setDetailsPrice(10.0);
        productInsertRequestDTO1.setDetailsDescription("description");
        productInsertRequestDTO1.setDetailsColor("color");
        ProductInsertRequestDTO productInsertRequestDTO2 = new ProductInsertRequestDTO();
        assertNotEquals(productInsertRequestDTO1, productInsertRequestDTO2);
        productInsertRequestDTO2.setName(productInsertRequestDTO1.getName());
        productInsertRequestDTO2.setStock(productInsertRequestDTO1.getStock());
        productInsertRequestDTO2.setDetailsPrice(productInsertRequestDTO1.getDetailsPrice());
        productInsertRequestDTO2.setDetailsDescription(productInsertRequestDTO1.getDetailsDescription());
        productInsertRequestDTO2.setDetailsColor(productInsertRequestDTO1.getDetailsColor());
        assertEquals(productInsertRequestDTO1, productInsertRequestDTO2);
    }

    @Test
    void testToString() {
        ProductInsertRequestDTO productInsertRequestDTO = new ProductInsertRequestDTO();
        productInsertRequestDTO.setName("name");
        productInsertRequestDTO.setStock(1);
        productInsertRequestDTO.setDetailsPrice(10.0);
        productInsertRequestDTO.setDetailsDescription("description");
        productInsertRequestDTO.setDetailsColor("color");
        assertThat(productInsertRequestDTO.toString())
            .hasToString(
                "ProductInsertRequestDTO{" +
                "name='" +
                productInsertRequestDTO.getName() +
                '\'' +
                ", stock=" +
                productInsertRequestDTO.getStock() +
                ", detailsPrice=" +
                productInsertRequestDTO.getDetailsPrice() +
                ", detailsDescription='" +
                productInsertRequestDTO.getDetailsDescription() +
                '\'' +
                ", detailsColor='" +
                productInsertRequestDTO.getDetailsColor() +
                '\'' +
                '}'
            );
    }

    @Test
    void testHashCode() {
        ProductInsertRequestDTO productInsertRequestDTO = new ProductInsertRequestDTO();
        productInsertRequestDTO.setName("name");
        productInsertRequestDTO.setStock(1);
        productInsertRequestDTO.setDetailsPrice(10.0);
        productInsertRequestDTO.setDetailsDescription("description");
        productInsertRequestDTO.setDetailsColor("color");
        ProductInsertRequestDTO productInsertRequestDTO1 = new ProductInsertRequestDTO();
        productInsertRequestDTO1.setName(productInsertRequestDTO.getName());
        productInsertRequestDTO1.setStock(productInsertRequestDTO.getStock());
        productInsertRequestDTO1.setDetailsPrice(productInsertRequestDTO.getDetailsPrice());
        productInsertRequestDTO1.setDetailsDescription(productInsertRequestDTO.getDetailsDescription());
        productInsertRequestDTO1.setDetailsColor(productInsertRequestDTO.getDetailsColor());
        assertThat(productInsertRequestDTO).hasSameHashCodeAs(productInsertRequestDTO1);
    }

    @Test
    void testEqualSameObject() {
        ProductInsertRequestDTO productInsertRequestDTO1 = new ProductInsertRequestDTO();
        productInsertRequestDTO1.setName("name");
        productInsertRequestDTO1.setStock(1);
        productInsertRequestDTO1.setDetailsPrice(10.0);
        productInsertRequestDTO1.setDetailsDescription("description");
        productInsertRequestDTO1.setDetailsColor("color");
        ProductInsertRequestDTO productInsertRequestDTO2 = productInsertRequestDTO1;
        assertThat(productInsertRequestDTO1).isEqualTo(productInsertRequestDTO2);
    }
}
