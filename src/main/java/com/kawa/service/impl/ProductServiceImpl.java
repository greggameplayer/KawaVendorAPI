package com.kawa.service.impl;

import com.kawa.domain.bean.Product;
import com.kawa.domain.bean.ProductWithoutId;
import com.kawa.management.MongoRequestService;
import com.kawa.service.ProductService;
import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindAllMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductInsertMongoRequestDTO;
import com.kawa.service.dto.response.ProductInsertResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindAllMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import com.kawa.service.mapper.request.ProductFindOneMongoRequestMapper;
import com.kawa.service.mapper.request.ProductInsertMongoRequestMapper;
import com.kawa.service.mapper.response.ProductFindAllMongoResponseMapper;
import com.kawa.service.mapper.response.ProductFindOneMongoResponseMapper;
import com.kawa.service.mapper.response.ProductInsertMongoResponseMapper;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Product}.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductFindOneMongoRequestMapper productFindOneMongoRequestMapper;

    private final ProductFindOneMongoResponseMapper productFindOneMongoResponseMapper;

    private final ProductFindAllMongoResponseMapper productFindAllMongoResponseMapper;

    private final ProductInsertMongoRequestMapper productInsertMongoRequestMapper;

    private final ProductInsertMongoResponseMapper productInsertMongoResponseMapper;

    private final MongoRequestService mongoRequestService;

    public ProductServiceImpl(
        ProductFindOneMongoRequestMapper productFindOneMongoRequestMapper,
        MongoRequestService mongoRequestService,
        ProductFindOneMongoResponseMapper productFindOneMongoResponseMapper,
        ProductFindAllMongoResponseMapper productFindAllMongoResponseMapper,
        ProductInsertMongoRequestMapper productInsertMongoRequestMapper,
        ProductInsertMongoResponseMapper productInsertMongoResponseMapper
    ) {
        this.productFindOneMongoRequestMapper = productFindOneMongoRequestMapper;
        this.mongoRequestService = mongoRequestService;
        this.productFindOneMongoResponseMapper = productFindOneMongoResponseMapper;
        this.productFindAllMongoResponseMapper = productFindAllMongoResponseMapper;
        this.productInsertMongoRequestMapper = productInsertMongoRequestMapper;
        this.productInsertMongoResponseMapper = productInsertMongoResponseMapper;
    }

    @Override
    public ProductResponseDTO getProduct(String id) {
        ProductFindOneMongoRequestDTO mongoRequestDTO = productFindOneMongoRequestMapper.toDto(id);
        return productFindOneMongoResponseMapper.toEntity(
            mongoRequestService.findOne(mongoRequestDTO, ProductFindOneMongoResponseDTO.class)
        );
    }

    @Override
    public ProductResponseDTO getProducts() {
        ProductFindAllMongoRequestDTO mongoRequestDTO = new ProductFindAllMongoRequestDTO();
        return productFindAllMongoResponseMapper.toEntity(
            mongoRequestService.findAll(mongoRequestDTO, ProductFindAllMongoResponseDTO.class)
        );
    }

    @Override
    public ProductInsertResponseDTO insertProduct(ProductWithoutId product) {
        ProductInsertRequestDTO requestDTO = productInsertMongoRequestMapper.toDto(product);
        ProductInsertMongoRequestDTO mongoRequestDTO = productInsertMongoRequestMapper.toDto(requestDTO);

        return productInsertMongoResponseMapper.toEntity(mongoRequestService.insert(mongoRequestDTO, InsertMongoResponseDTO.class));
    }
}
