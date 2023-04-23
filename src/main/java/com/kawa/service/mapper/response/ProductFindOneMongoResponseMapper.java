package com.kawa.service.mapper.response;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import com.kawa.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductFindOneMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductFindOneMongoResponseMapper extends EntityMapper<ProductFindOneMongoResponseDTO, Product> {}
