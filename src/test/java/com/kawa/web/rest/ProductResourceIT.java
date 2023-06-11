package com.kawa.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.kawa.IntegrationTest;
import com.kawa.domain.bean.Product;
import com.kawa.management.MongoRequestService;
import com.kawa.security.AuthoritiesConstants;
import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.request.mongo.FindAllMongoRequestDTO;
import com.kawa.service.dto.request.mongo.FindOneMongoRequestDTO;
import com.kawa.service.dto.request.mongo.InsertMongoRequestDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindAllMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAD";

    private static final int DEFAULT_STOCK = 1;

    private static final double DEFAULT_PRICE = 1D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAC";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";

    private static final String DEFAULT_ID = "AAAAAAAAAB";

    private static final String DEFAULT_CREATED_AT = "2021-09-22T09:00:00.000Z";

    private static final String ENTITY_API_URL = "/api/products";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    private MockMvc restProductMockMvc;

    private static final Logger log = (Logger) LoggerFactory.getLogger(ProductResource.class);

    private static ListAppender<ILoggingEvent> memoryAppender;

    @MockBean
    private MongoRequestService mongoRequestService;

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
    void insertProduct() throws Exception {
        InsertMongoResponseDTO insertMongoResponseDTO = new InsertMongoResponseDTO();
        insertMongoResponseDTO.setInsertedId(DEFAULT_ID);

        ProductInsertRequestDTO productInsertRequestDTO = new ProductInsertRequestDTO();
        productInsertRequestDTO.setName(DEFAULT_NAME);
        productInsertRequestDTO.setStock(DEFAULT_STOCK);
        productInsertRequestDTO.setDetailsPrice(DEFAULT_PRICE);
        productInsertRequestDTO.setDetailsDescription(DEFAULT_DESCRIPTION);
        productInsertRequestDTO.setDetailsColor(DEFAULT_COLOR);

        // Mock RestTemplate returning InsertMongoResponseDTO class
        Mockito
            .when(mongoRequestService.insert(Mockito.any(InsertMongoRequestDTO.class), Mockito.eq(InsertMongoResponseDTO.class)))
            .thenReturn(insertMongoResponseDTO);

        restProductMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productInsertRequestDTO))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.insertedId").value(DEFAULT_ID));

        assertThat(memoryAppender.list)
            .extracting(ILoggingEvent::getFormattedMessage)
            .containsExactly(
                "REST request to save Product : ProductInsertRequestDTO{name='AAAAAAAAAD', stock=1, detailsPrice=1.0, detailsDescription='AAAAAAAAAC', detailsColor='AAAAAAAAAA'}"
            );
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.USER })
    void getProduct() throws Exception {
        ProductFindOneMongoResponseDTO productFindOneMongoResponseDTO = new ProductFindOneMongoResponseDTO();
        productFindOneMongoResponseDTO.setDocumentName(DEFAULT_NAME);
        productFindOneMongoResponseDTO.setDocumentStock(DEFAULT_STOCK);
        productFindOneMongoResponseDTO.setDocumentDetailsPrice(DEFAULT_PRICE);
        productFindOneMongoResponseDTO.setDocumentDetailsDescription(DEFAULT_DESCRIPTION);
        productFindOneMongoResponseDTO.setDocumentDetailsColor(DEFAULT_COLOR);
        productFindOneMongoResponseDTO.setDocumentCreatedAt(DATE_FORMAT.parse(DEFAULT_CREATED_AT));
        productFindOneMongoResponseDTO.setDocumentId(DEFAULT_ID);

        Mockito
            .when(mongoRequestService.findOne(Mockito.any(FindOneMongoRequestDTO.class), Mockito.eq(ProductFindOneMongoResponseDTO.class)))
            .thenReturn(productFindOneMongoResponseDTO);

        restProductMockMvc
            .perform(get(ENTITY_API_URL + "/{id}", DEFAULT_ID))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.products[0].name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.products[0].stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.products[0].details.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.products[0].details.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.products[0].details.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.products[0].createdAt").value(DEFAULT_CREATED_AT))
            .andExpect(jsonPath("$.products[0]._id").value(DEFAULT_ID));

        assertThat(memoryAppender.list)
            .extracting(ILoggingEvent::getFormattedMessage)
            .containsExactly("REST request to get Product : " + DEFAULT_ID);
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.USER })
    void getAllProducts() throws Exception {
        String product2Name = "BBBBBBBBBA";
        int product2Stock = 2;
        double product2Price = 2D;
        String product2Description = "BBBBBBBBBT";
        String product2Color = "BBBBBBBBBC";
        String product2Id = "BBBBBBBBBD";
        String product2CreatedAt = "2021-09-26T09:00:00.000Z";

        Product product1 = createProduct(
            DEFAULT_NAME,
            DEFAULT_STOCK,
            DEFAULT_PRICE,
            DEFAULT_DESCRIPTION,
            DEFAULT_COLOR,
            DEFAULT_ID,
            DATE_FORMAT.parse(DEFAULT_CREATED_AT)
        );

        Product product2 = createProduct(
            product2Name,
            product2Stock,
            product2Price,
            product2Description,
            product2Color,
            product2Id,
            DATE_FORMAT.parse(product2CreatedAt)
        );

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        ProductFindAllMongoResponseDTO productFindAllMongoResponseDTO = new ProductFindAllMongoResponseDTO();
        productFindAllMongoResponseDTO.setDocuments(products);

        Mockito
            .when(mongoRequestService.findAll(Mockito.any(FindAllMongoRequestDTO.class), Mockito.eq(ProductFindAllMongoResponseDTO.class)))
            .thenReturn(productFindAllMongoResponseDTO);

        restProductMockMvc
            .perform(get(ENTITY_API_URL))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.products[0].name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.products[0].stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.products[0].details.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.products[0].details.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.products[0].details.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.products[0].createdAt").value(DEFAULT_CREATED_AT))
            .andExpect(jsonPath("$.products[0]._id").value(DEFAULT_ID))
            .andExpect(jsonPath("$.products[1].name").value(product2Name))
            .andExpect(jsonPath("$.products[1].stock").value(product2Stock))
            .andExpect(jsonPath("$.products[1].details.price").value(product2Price))
            .andExpect(jsonPath("$.products[1].details.description").value(product2Description))
            .andExpect(jsonPath("$.products[1].details.color").value(product2Color))
            .andExpect(jsonPath("$.products[1].createdAt").value(product2CreatedAt))
            .andExpect(jsonPath("$.products[1]._id").value(product2Id));

        assertThat(memoryAppender.list).extracting(ILoggingEvent::getFormattedMessage).containsExactly("REST request to get all Products");
    }
}
