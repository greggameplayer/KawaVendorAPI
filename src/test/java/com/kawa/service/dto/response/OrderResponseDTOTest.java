package com.kawa.service.dto.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawa.domain.bean.Order;
import com.kawa.domain.bean.Product;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderResponseDTOTest {

    private static final String JSON =
        "{\"orders\":[{\"_id\":\"5ff9f9b0e4b0b5b9b0b9b9b9\",\"customerId\":\"f6fsef6s11e5\",\"products\":[{\"_id\":\"5ff9f9b0e4b0b5b9b0b9b9b9\",\"createdAt\":\"2021-01-09T15:00:00.000Z\",\"name\":\"Product 1\",\"stock\":1,\"details\":{\"price\":10.0,\"description\":\"Description 1\",\"color\":\"Color 1\"}}],\"createdAt\":\"2021-01-10T15:00:00.000Z\"}]}";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeAll
    static void setUp() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @Test
    void whenUsingJsonNode() throws JsonProcessingException {
        OrderResponseDTO orderResponseDTO = new ObjectMapper().readerFor(OrderResponseDTO.class).readValue(JSON);
        assertEquals(1, orderResponseDTO.getOrders().size());
        Order firstOrder = orderResponseDTO.getOrders().get(0);
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", firstOrder.getId());
        assertEquals("f6fsef6s11e5", firstOrder.getCustomerId());
        assertEquals(1, firstOrder.getProducts().size());
        Product firstProduct = firstOrder.getProducts().get(0);
        assertEquals("5ff9f9b0e4b0b5b9b0b9b9b9", firstProduct.getId());
        assertEquals("Product 1", firstProduct.getName());
        assertEquals(1, firstProduct.getStock());
        assertEquals("Description 1", firstProduct.getDetailsDescription());
        assertEquals("Color 1", firstProduct.getDetailsColor());
        assertEquals(10.0, firstProduct.getDetailsPrice());
        assertEquals("2021-01-10T15:00:00.000Z", dateFormat.format(firstOrder.getCreatedAt()));
        assertEquals("2021-01-09T15:00:00.000Z", dateFormat.format(firstProduct.getCreatedAt()));
    }

    @Test
    void dtoEqualsVerifier() {
        OrderResponseDTO orderResponseDTO1 = new OrderResponseDTO();
        Order order = new Order();
        order.setId("1");
        order.setCustomerId("1");
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId("1");
        product.setCreatedAt(Date.from(Instant.now()));
        product.setName("name");
        product.setStock(1);
        product.setDetailsPrice(10.0);
        product.setDetailsDescription("description");
        product.setDetailsColor("color");
        products.add(product);
        order.setProducts(products);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orderResponseDTO1.setOrders(orders);
        OrderResponseDTO orderResponseDTO2 = new OrderResponseDTO();
        assertNotEquals(orderResponseDTO1, orderResponseDTO2);
        orderResponseDTO2.setOrders(orders);
        assertEquals(orderResponseDTO1, orderResponseDTO2);
    }

    @Test
    void testToString() {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
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
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId("1");
        order.setCustomerId("1");
        order.setProducts(products);
        order.setCreatedAt(createdAt);
        orders.add(order);
        orderResponseDTO.setOrders(orders);
        assertEquals(
            "OrderResponseDTO{orders=[Order{id='1', createdAt='" +
            dateFormat.format(createdAt) +
            "', customerId='1', products=[Product{id='1', createdAt='" +
            dateFormat.format(createdAt) +
            "', name='name', stock=1, detailsPrice=10.0, detailsDescription='description', detailsColor='color'}]" +
            "}]}",
            orderResponseDTO.toString()
        );
    }

    @Test
    void testHashcode() {
        OrderResponseDTO orderResponseDTO1 = new OrderResponseDTO();
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
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId("1");
        order.setCustomerId("1");
        order.setProducts(products);
        orderResponseDTO1.setOrders(orders);
        OrderResponseDTO orderResponseDTO2 = new OrderResponseDTO();
        orderResponseDTO2.setOrders(orders);
        assertThat(orderResponseDTO1).hasSameHashCodeAs(orderResponseDTO2);
    }

    @Test
    void testEqualSameObject() {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrders(new ArrayList<>());
        OrderResponseDTO orderResponseDTO2 = orderResponseDTO;
        assertThat(orderResponseDTO).isEqualTo(orderResponseDTO2);
    }
}
