package com.kawa.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.kawa.IntegrationTest;
import com.kawa.domain.bean.Order;
import com.kawa.domain.bean.Product;
import com.kawa.management.MongoRequestService;
import com.kawa.security.AuthoritiesConstants;
import com.kawa.service.dto.request.OrderInsertRequestDTO;
import com.kawa.service.dto.request.mongo.FindAllMongoRequestDTO;
import com.kawa.service.dto.request.mongo.FindOneMongoRequestDTO;
import com.kawa.service.dto.request.mongo.InsertMongoRequestDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import com.kawa.service.dto.response.mongo.OrderFindAllMongoResponseDTO;
import com.kawa.service.dto.response.mongo.OrderFindOneMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
public class OrderResourceIT {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";

    private static final String DEFAULT_ID = "AAAAAAAAAB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAD";

    private static final String DEFAULT_CREATED_AT = "2021-09-23T09:00:00.000Z";

    private static final int DEFAULT_PRODUCT_STOCK = 1;

    private static final double DEFAULT_PRODUCT_PRICE = 1D;

    private static final String DEFAULT_PRODUCT_DESCRIPTION = "AAAAAAAAAC";

    private static final String DEFAULT_PRODUCT_COLOR = "AAAAAAAAAA";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAB";

    private static final String DEFAULT_PRODUCT_CREATED_AT = "2021-09-22T09:00:00.000Z";

    private static final String ENTITY_API_URL = "/api/orders";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    private MockMvc restOrderMockMvc;

    private static final Logger log = (Logger) LoggerFactory.getLogger(OrderResource.class);

    private static ListAppender<ILoggingEvent> memoryAppender;

    @MockBean
    private MongoRequestService mongoRequestService;

    public static Order createOrder(String customerId, String id, Date createdAt) {
        Order order = new Order();

        order.setCustomerId(customerId);
        order.setId(id);
        order.setCreatedAt(createdAt);

        return order;
    }

    public static Product createProduct(
        String name,
        Integer stock,
        Double price,
        String description,
        String color,
        String id,
        Date createdAt
    ) {
        Product product = new Product();

        product.setName(name);
        product.setStock(stock);
        product.setDetailsPrice(price);
        product.setDetailsDescription(description);
        product.setDetailsColor(color);
        product.setId(id);
        product.setCreatedAt(createdAt);

        return product;
    }

    @BeforeAll
    public static void setup() {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @BeforeEach
    public void initTest() {
        memoryAppender = new ListAppender<>();

        memoryAppender.start();
        log.addAppender(memoryAppender);
    }

    @AfterEach
    public void teardown() {
        memoryAppender.stop();
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.USER })
    void insertOrder() throws Exception {
        InsertMongoResponseDTO insertMongoResponseDTO = new InsertMongoResponseDTO();
        insertMongoResponseDTO.setInsertedId(DEFAULT_ID);
        List<String> productIds = new ArrayList<>();

        productIds.add(DEFAULT_PRODUCT_ID);

        OrderInsertRequestDTO orderInsertRequestDTO = new OrderInsertRequestDTO();
        orderInsertRequestDTO.setCustomerId(DEFAULT_CUSTOMER_ID);
        orderInsertRequestDTO.productIds(productIds);

        ProductFindOneMongoResponseDTO productFindOneMongoResponseDTO = new ProductFindOneMongoResponseDTO();
        productFindOneMongoResponseDTO.setDocumentCreatedAt(DATE_FORMAT.parse(DEFAULT_PRODUCT_CREATED_AT));
        productFindOneMongoResponseDTO.setDocumentId(DEFAULT_PRODUCT_ID);
        productFindOneMongoResponseDTO.setDocumentName(DEFAULT_PRODUCT_NAME);
        productFindOneMongoResponseDTO.setDocumentStock(DEFAULT_PRODUCT_STOCK);
        productFindOneMongoResponseDTO.setDocumentDetailsPrice(DEFAULT_PRODUCT_PRICE);
        productFindOneMongoResponseDTO.setDocumentDetailsDescription(DEFAULT_PRODUCT_DESCRIPTION);
        productFindOneMongoResponseDTO.setDocumentDetailsColor(DEFAULT_PRODUCT_COLOR);

        Mockito
            .when(mongoRequestService.insert(Mockito.any(InsertMongoRequestDTO.class), Mockito.eq(InsertMongoResponseDTO.class)))
            .thenReturn(insertMongoResponseDTO);

        Mockito
            .when(mongoRequestService.findOne(Mockito.any(FindOneMongoRequestDTO.class), Mockito.eq(ProductFindOneMongoResponseDTO.class)))
            .thenReturn(productFindOneMongoResponseDTO);

        restOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType("application/json").content(TestUtil.convertObjectToJsonBytes(orderInsertRequestDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.insertedId").value(DEFAULT_ID));

        assertThat(memoryAppender.list)
            .extracting(ILoggingEvent::getFormattedMessage)
            .containsExactly("REST request to save Order : OrderInsertRequestDTO{customerId='AAAAAAAAAA', productIds=[AAAAAAAAAB]}");
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.USER })
    void getOrder() throws Exception {
        OrderFindOneMongoResponseDTO orderFindOneMongoResponseDTO = new OrderFindOneMongoResponseDTO();
        orderFindOneMongoResponseDTO.setDocumentCreatedAt(DATE_FORMAT.parse(DEFAULT_CREATED_AT));
        orderFindOneMongoResponseDTO.setDocumentId(DEFAULT_ID);
        orderFindOneMongoResponseDTO.setDocumentCustomerId(DEFAULT_CUSTOMER_ID);

        List<Product> products = new ArrayList<>();
        products.add(
            createProduct(
                DEFAULT_PRODUCT_NAME,
                DEFAULT_PRODUCT_STOCK,
                DEFAULT_PRODUCT_PRICE,
                DEFAULT_PRODUCT_DESCRIPTION,
                DEFAULT_PRODUCT_COLOR,
                DEFAULT_PRODUCT_ID,
                DATE_FORMAT.parse(DEFAULT_PRODUCT_CREATED_AT)
            )
        );

        orderFindOneMongoResponseDTO.setDocumentProducts(products);

        Mockito
            .when(mongoRequestService.findOne(Mockito.any(FindOneMongoRequestDTO.class), Mockito.eq(OrderFindOneMongoResponseDTO.class)))
            .thenReturn(orderFindOneMongoResponseDTO);

        restOrderMockMvc
            .perform(get(ENTITY_API_URL + "/{id}", DEFAULT_ID))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.orders[0]._id").value(DEFAULT_ID))
            .andExpect(jsonPath("$.orders[0].customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.orders[0].products[0].name").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.orders[0].products[0].stock").value(DEFAULT_PRODUCT_STOCK))
            .andExpect(jsonPath("$.orders[0].products[0].details.price").value(DEFAULT_PRODUCT_PRICE))
            .andExpect(jsonPath("$.orders[0].products[0].details.description").value(DEFAULT_PRODUCT_DESCRIPTION))
            .andExpect(jsonPath("$.orders[0].products[0].details.color").value(DEFAULT_PRODUCT_COLOR))
            .andExpect(jsonPath("$.orders[0].products[0]._id").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.orders[0].products[0].createdAt").value(DEFAULT_PRODUCT_CREATED_AT));

        assertThat(memoryAppender.list)
            .extracting(ILoggingEvent::getFormattedMessage)
            .containsExactly("REST request to get Order : " + DEFAULT_ID);
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.USER })
    void getAllOrders() throws Exception {
        String order2Id = "order2Id";
        String order2CustomerId = "order2CustomerId";
        String order2CreatedAt = "2021-01-02T00:00:00.000Z";
        String order2Product1Name = "order2Product1Name";
        int order2Product1Stock = 1;
        double order2Product1Price = 1D;
        String order2Product1Description = "order2Product1Description";
        String order2Product1Color = "order2Product1Color";
        String order2Product1Id = "order2Product1Id";
        String order2Product1CreatedAt = "2021-01-01T00:00:00.000Z";

        Order order1 = createOrder(DEFAULT_CUSTOMER_ID, DEFAULT_ID, DATE_FORMAT.parse(DEFAULT_CREATED_AT));
        List<Product> products1 = new ArrayList<>();
        products1.add(
            createProduct(
                DEFAULT_PRODUCT_NAME,
                DEFAULT_PRODUCT_STOCK,
                DEFAULT_PRODUCT_PRICE,
                DEFAULT_PRODUCT_DESCRIPTION,
                DEFAULT_PRODUCT_COLOR,
                DEFAULT_PRODUCT_ID,
                DATE_FORMAT.parse(DEFAULT_PRODUCT_CREATED_AT)
            )
        );
        order1.setProducts(products1);

        Order order2 = createOrder(order2CustomerId, order2Id, DATE_FORMAT.parse(order2CreatedAt));
        List<Product> products2 = new ArrayList<>();
        products2.add(
            createProduct(
                order2Product1Name,
                order2Product1Stock,
                order2Product1Price,
                order2Product1Description,
                order2Product1Color,
                order2Product1Id,
                DATE_FORMAT.parse(order2Product1CreatedAt)
            )
        );
        order2.setProducts(products2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        OrderFindAllMongoResponseDTO orderFindAllMongoResponseDTO = new OrderFindAllMongoResponseDTO();
        orderFindAllMongoResponseDTO.setDocuments(orders);

        Mockito
            .when(mongoRequestService.findAll(Mockito.any(FindAllMongoRequestDTO.class), Mockito.eq(OrderFindAllMongoResponseDTO.class)))
            .thenReturn(orderFindAllMongoResponseDTO);

        restOrderMockMvc
            .perform(get(ENTITY_API_URL))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.orders[0]._id").value(DEFAULT_ID))
            .andExpect(jsonPath("$.orders[0].createdAt").value(DEFAULT_CREATED_AT))
            .andExpect(jsonPath("$.orders[0].customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.orders[0].products[0].name").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.orders[0].products[0].stock").value(DEFAULT_PRODUCT_STOCK))
            .andExpect(jsonPath("$.orders[0].products[0].details.price").value(DEFAULT_PRODUCT_PRICE))
            .andExpect(jsonPath("$.orders[0].products[0].details.description").value(DEFAULT_PRODUCT_DESCRIPTION))
            .andExpect(jsonPath("$.orders[0].products[0].details.color").value(DEFAULT_PRODUCT_COLOR))
            .andExpect(jsonPath("$.orders[0].products[0]._id").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.orders[0].products[0].createdAt").value(DEFAULT_PRODUCT_CREATED_AT))
            .andExpect(jsonPath("$.orders[1]._id").value(order2Id))
            .andExpect(jsonPath("$.orders[1].createdAt").value(order2CreatedAt))
            .andExpect(jsonPath("$.orders[1].customerId").value(order2CustomerId))
            .andExpect(jsonPath("$.orders[1].products[0].name").value(order2Product1Name))
            .andExpect(jsonPath("$.orders[1].products[0].stock").value(order2Product1Stock))
            .andExpect(jsonPath("$.orders[1].products[0].details.price").value(order2Product1Price))
            .andExpect(jsonPath("$.orders[1].products[0].details.description").value(order2Product1Description))
            .andExpect(jsonPath("$.orders[1].products[0].details.color").value(order2Product1Color))
            .andExpect(jsonPath("$.orders[1].products[0]._id").value(order2Product1Id))
            .andExpect(jsonPath("$.orders[1].products[0].createdAt").value(order2Product1CreatedAt));

        assertThat(memoryAppender.list).extracting(ILoggingEvent::getFormattedMessage).containsExactly("REST request to get all Orders");
    }
}
