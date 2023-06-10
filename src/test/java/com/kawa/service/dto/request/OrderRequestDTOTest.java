package com.kawa.service.dto.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawa.service.dto.request.ProductRequestDTO;
import org.junit.jupiter.api.Test;

class OrderRequestDTOTest {

    private static final String JSON_WITH_ID = "{\"id\":\"5ff9f9b0e4b0b5b9b0b9b9b9\"}";
    private static final String JSON_WITHOUT_ID = "{\"id\":null}";

    @Test
    void whenUsingJsonNodeWithId() throws JsonProcessingException {
        OrderRequestDTO orderRequestDTO = new ObjectMapper().readerFor(OrderRequestDTO.class).readValue(JSON_WITH_ID);
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", orderRequestDTO.getId());
    }

    @Test
    void whenUsingJsonNodeWithoutId() throws JsonProcessingException {
        OrderRequestDTO orderRequestDTO = new ObjectMapper().readerFor(OrderRequestDTO.class).readValue(JSON_WITHOUT_ID);
        assertNull(orderRequestDTO.getId());
    }

    @Test
    void dtoEqualsVerifier() {
        OrderRequestDTO orderRequestDTO1 = new OrderRequestDTO();
        orderRequestDTO1.setId("id");
        OrderRequestDTO orderRequestDTO2 = new OrderRequestDTO();
        assertNotEquals(orderRequestDTO1, orderRequestDTO2);
        orderRequestDTO2.setId(orderRequestDTO1.getId());
        assertEquals(orderRequestDTO1, orderRequestDTO2);
    }

    @Test
    void testToString() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setId("id");
        assertEquals("OrderRequestDTO{id='id'}", orderRequestDTO.toString());
    }

    @Test
    void testHashcode() {
        OrderRequestDTO orderRequestDTO1 = new OrderRequestDTO();
        orderRequestDTO1.setId("id");
        OrderRequestDTO orderRequestDTO2 = new OrderRequestDTO();
        orderRequestDTO2.setId(orderRequestDTO1.getId());
        assertEquals(orderRequestDTO1.hashCode(), orderRequestDTO2.hashCode());
    }

    @Test
    void testEqualSameObject() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setId("id");
        OrderRequestDTO orderRequestDTO1 = orderRequestDTO;
        assertThat(orderRequestDTO).isEqualTo(orderRequestDTO1);
    }
}
