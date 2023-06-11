package com.kawa.service.dto.request;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderInsertRequestDTOTest {

    private static final String JSON =
        "{\"customerId\":\"f6fsef6s11e5\",\"productIds\":[\"5ff9f9b0e4b0b5b9b0b9b9b9\",\"5ff9f9b0e4b0b5b9b0b9b9b8\"]}";

    @Test
    void whenUsingJsonNode() throws Exception {
        OrderInsertRequestDTO orderInsertRequestDTO = new ObjectMapper().readerFor(OrderInsertRequestDTO.class).readValue(JSON);
        assertEquals("f6fsef6s11e5", orderInsertRequestDTO.getCustomerId());
        assertEquals(2, orderInsertRequestDTO.getProductIds().size());
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", orderInsertRequestDTO.getProductIds().get(0));
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b8", orderInsertRequestDTO.getProductIds().get(1));
    }

    @Test
    void dtoEqualsVerifier() {
        OrderInsertRequestDTO orderInsertRequestDTO1 = new OrderInsertRequestDTO();
        orderInsertRequestDTO1.setCustomerId("customerId");
        List<String> productIds1 = new ArrayList<>();
        productIds1.add("5ff9f9b0e4b0b5b9b0b9b9b9");
        productIds1.add("5ff9f9b0e4b0b5b9b0b9b9b8");
        orderInsertRequestDTO1.setProductIds(productIds1);
        OrderInsertRequestDTO orderInsertRequestDTO2 = new OrderInsertRequestDTO();
        assertNotEquals(orderInsertRequestDTO1, orderInsertRequestDTO2);
        orderInsertRequestDTO2.setCustomerId(orderInsertRequestDTO1.getCustomerId());
        assertNotEquals(orderInsertRequestDTO1, orderInsertRequestDTO2);
        orderInsertRequestDTO2.setProductIds(orderInsertRequestDTO1.getProductIds());
        assertEquals(orderInsertRequestDTO1, orderInsertRequestDTO2);
    }

    @Test
    void testToString() {
        OrderInsertRequestDTO orderInsertRequestDTO = new OrderInsertRequestDTO();
        orderInsertRequestDTO.setCustomerId("customerId");
        List<String> productIds = new ArrayList<>();
        productIds.add("5ff9f9b0e4b0b5b9b0b9b9b9");
        productIds.add("5ff9f9b0e4b0b5b9b0b9b9b8");
        orderInsertRequestDTO.setProductIds(productIds);
        assertThat(orderInsertRequestDTO.toString())
            .hasToString(
                "OrderInsertRequestDTO{" +
                "customerId='customerId'" +
                ", productIds=[" +
                "5ff9f9b0e4b0b5b9b0b9b9b9" +
                ", 5ff9f9b0e4b0b5b9b0b9b9b8" +
                ']' +
                '}'
            );
    }

    @Test
    void testHashCode() {
        OrderInsertRequestDTO orderInsertRequestDTO = new OrderInsertRequestDTO();
        orderInsertRequestDTO.setCustomerId("customerId");
        List<String> productIds = new ArrayList<>();
        productIds.add("5ff9f9b0e4b0b5b9b0b9b9b9");
        productIds.add("5ff9f9b0e4b0b5b9b0b9b9b8");
        orderInsertRequestDTO.setProductIds(productIds);
        OrderInsertRequestDTO orderInsertRequestDTO1 = new OrderInsertRequestDTO();
        orderInsertRequestDTO1.setCustomerId(orderInsertRequestDTO.getCustomerId());
        orderInsertRequestDTO1.setProductIds(orderInsertRequestDTO.getProductIds());
        assertThat(orderInsertRequestDTO).hasSameHashCodeAs(orderInsertRequestDTO1);
    }

    @Test
    void testEqualSameObject() {
        OrderInsertRequestDTO orderInsertRequestDTO1 = new OrderInsertRequestDTO();
        orderInsertRequestDTO1.setCustomerId("customerId");
        List<String> productIds1 = new ArrayList<>();
        productIds1.add("5ff9f9b0e4b0b5b9b0b9b9b9");
        productIds1.add("5ff9f9b0e4b0b5b9b0b9b9b8");
        orderInsertRequestDTO1.setProductIds(productIds1);
        OrderInsertRequestDTO orderInsertRequestDTO2 = orderInsertRequestDTO1;
        assertThat(orderInsertRequestDTO1).isEqualTo(orderInsertRequestDTO2);
    }
}
