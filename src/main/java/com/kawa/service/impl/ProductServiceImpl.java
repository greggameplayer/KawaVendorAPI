package com.kawa.service.impl;

import com.kawa.domain.bean.Product;
import com.kawa.management.MongoRequestService;
import com.kawa.service.ProductService;
import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindAllMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductInsertMongoRequestDTO;
import com.kawa.service.dto.response.InsertResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindAllMongoResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import com.kawa.service.mapper.request.FindOneMongoRequestMapper;
import com.kawa.service.mapper.request.InsertMongoRequestMapper;
import com.kawa.service.mapper.response.FindAllMongoResponseMapper;
import com.kawa.service.mapper.response.FindOneMongoResponseMapper;
import com.kawa.service.mapper.response.InsertMongoResponseMapper;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Product}.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final FindOneMongoRequestMapper findOneMongoRequestMapper;

    private final FindOneMongoResponseMapper findOneMongoResponseMapper;

    private final FindAllMongoResponseMapper findAllMongoResponseMapper;

    private final InsertMongoRequestMapper insertMongoRequestMapper;

    private final InsertMongoResponseMapper insertMongoResponseMapper;

    private final MongoRequestService mongoRequestService;

    public ProductServiceImpl(
        FindOneMongoRequestMapper findOneMongoRequestMapper,
        MongoRequestService mongoRequestService,
        FindOneMongoResponseMapper findOneMongoResponseMapper,
        FindAllMongoResponseMapper findAllMongoResponseMapper,
        InsertMongoRequestMapper insertMongoRequestMapper,
        InsertMongoResponseMapper insertMongoResponseMapper
    ) {
        this.findOneMongoRequestMapper = findOneMongoRequestMapper;
        this.mongoRequestService = mongoRequestService;
        this.findOneMongoResponseMapper = findOneMongoResponseMapper;
        this.findAllMongoResponseMapper = findAllMongoResponseMapper;
        this.insertMongoRequestMapper = insertMongoRequestMapper;
        this.insertMongoResponseMapper = insertMongoResponseMapper;
    }

    @Override
    public ProductResponseDTO getProduct(String id) {
        ProductFindOneMongoRequestDTO mongoRequestDTO = findOneMongoRequestMapper.toDto(id);
        return findOneMongoResponseMapper.toEntity(mongoRequestService.findOne(mongoRequestDTO, ProductFindOneMongoResponseDTO.class));
    }

    @Override
    public ProductResponseDTO getProducts() {
        ProductFindAllMongoRequestDTO mongoRequestDTO = new ProductFindAllMongoRequestDTO();
        return findAllMongoResponseMapper.toEntity(mongoRequestService.findAll(mongoRequestDTO, ProductFindAllMongoResponseDTO.class));
    }

    @Override
    public InsertResponseDTO insertProduct(ProductInsertRequestDTO requestDTO) {
        ProductInsertMongoRequestDTO mongoRequestDTO = insertMongoRequestMapper.toDto(requestDTO, new Date());

        return insertMongoResponseMapper.toEntity(mongoRequestService.insert(mongoRequestDTO, InsertMongoResponseDTO.class));
    }
}
