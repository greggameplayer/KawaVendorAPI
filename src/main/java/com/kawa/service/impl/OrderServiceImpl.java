package com.kawa.service.impl;

import com.kawa.domain.bean.Order;
import com.kawa.domain.bean.Product;
import com.kawa.management.MongoRequestService;
import com.kawa.service.OrderService;
import com.kawa.service.ProductService;
import com.kawa.service.dto.request.OrderInsertRequestDTO;
import com.kawa.service.dto.request.mongo.OrderInsertMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindAllMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import com.kawa.service.dto.response.InsertResponseDTO;
import com.kawa.service.dto.response.OrderResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import com.kawa.service.dto.response.mongo.OrderFindAllMongoResponseDTO;
import com.kawa.service.dto.response.mongo.OrderFindOneMongoResponseDTO;
import com.kawa.service.mapper.request.FindOneMongoRequestMapper;
import com.kawa.service.mapper.request.InsertMongoRequestMapper;
import com.kawa.service.mapper.response.FindAllMongoResponseMapper;
import com.kawa.service.mapper.response.FindOneMongoResponseMapper;
import com.kawa.service.mapper.response.InsertMongoResponseMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final FindOneMongoRequestMapper findOneMongoRequestMapper;

    private final FindOneMongoResponseMapper findOneMongoResponseMapper;

    private final FindAllMongoResponseMapper findAllMongoResponseMapper;

    private final InsertMongoRequestMapper insertMongoRequestMapper;

    private final InsertMongoResponseMapper insertMongoResponseMapper;

    private final MongoRequestService mongoRequestService;

    private final ProductService productService;

    public OrderServiceImpl(
        FindOneMongoRequestMapper findOneMongoRequestMapper,
        MongoRequestService mongoRequestService,
        FindOneMongoResponseMapper findOneMongoResponseMapper,
        FindAllMongoResponseMapper findAllMongoResponseMapper,
        InsertMongoRequestMapper insertMongoRequestMapper,
        InsertMongoResponseMapper insertMongoResponseMapper,
        ProductService productService
    ) {
        this.findOneMongoRequestMapper = findOneMongoRequestMapper;
        this.mongoRequestService = mongoRequestService;
        this.findOneMongoResponseMapper = findOneMongoResponseMapper;
        this.findAllMongoResponseMapper = findAllMongoResponseMapper;
        this.insertMongoRequestMapper = insertMongoRequestMapper;
        this.insertMongoResponseMapper = insertMongoResponseMapper;
        this.productService = productService;
    }

    @Override
    public OrderResponseDTO getOrder(String id) {
        ProductFindOneMongoRequestDTO mongoRequestDTO = findOneMongoRequestMapper.toDto(id);

        return findOneMongoResponseMapper.toOrderEntity(mongoRequestService.findOne(mongoRequestDTO, OrderFindOneMongoResponseDTO.class));
    }

    @Override
    public OrderResponseDTO getOrders() {
        ProductFindAllMongoRequestDTO mongoRequestDTO = new ProductFindAllMongoRequestDTO();

        return findAllMongoResponseMapper.toOrderEntity(mongoRequestService.findAll(mongoRequestDTO, OrderFindAllMongoResponseDTO.class));
    }

    @Override
    public InsertResponseDTO insertOrder(OrderInsertRequestDTO requestDTO) {
        OrderInsertMongoRequestDTO mongoRequestDTO = insertMongoRequestMapper.toOrderDto(requestDTO);
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < requestDTO.getProductIds().size(); i++) {
            ProductResponseDTO productResponseDTO = productService.getProduct(requestDTO.getProductIds().get(i));

            if (productResponseDTO.getProducts().isEmpty()) {
                throw new RuntimeException("Product not found");
            }

            products.add(productResponseDTO.getProducts().get(0));
        }
        mongoRequestDTO.setDocumentProducts(products);

        return insertMongoResponseMapper.toEntity(mongoRequestService.insert(mongoRequestDTO, InsertMongoResponseDTO.class));
    }
}
