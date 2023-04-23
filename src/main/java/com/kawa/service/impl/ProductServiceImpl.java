package com.kawa.service.impl;

import com.kawa.domain.bean.Product;
import com.kawa.management.MongoRequestService;
import com.kawa.service.ProductService;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import com.kawa.service.mapper.request.ProductFindOneMongoRequestMapper;
import com.kawa.service.mapper.response.ProductFindOneMongoResponseMapper;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Product}.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private static final String API_KEY = "IcpnqyikSl3vtUgwOMlodLyosYYkmQSrcOzZMpuyExEsQlZRJAsX2HSDXqou4EbK";

    private final ProductFindOneMongoRequestMapper productFindOneMongoRequestMapper;

    private final ProductFindOneMongoResponseMapper productFindOneMongoResponseMapper;

    private final MongoRequestService mongoRequestService;

    public ProductServiceImpl(
        ProductFindOneMongoRequestMapper productFindOneMongoRequestMapper,
        MongoRequestService mongoRequestService,
        ProductFindOneMongoResponseMapper productFindOneMongoResponseMapper
    ) {
        this.productFindOneMongoRequestMapper = productFindOneMongoRequestMapper;
        this.mongoRequestService = mongoRequestService;
        this.productFindOneMongoResponseMapper = productFindOneMongoResponseMapper;
    }

    @Override
    public Product getProduct(String id) {
        ProductFindOneMongoRequestDTO mongoRequestDTO = productFindOneMongoRequestMapper.toDto(id);
        return productFindOneMongoResponseMapper.toEntity(
            mongoRequestService.findOne(mongoRequestDTO, ProductFindOneMongoResponseDTO.class)
        );
    }
}
