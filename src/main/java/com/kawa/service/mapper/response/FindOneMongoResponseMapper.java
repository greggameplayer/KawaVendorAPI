package com.kawa.service.mapper.response;

import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the Response DTO {@link ProductResponseDTO} and its Mongo DTO {@link ProductFindOneMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface FindOneMongoResponseMapper {
    ProductResponseDTO toEntity(ProductFindOneMongoResponseDTO dtoList);

    ProductFindOneMongoResponseDTO toDto(ProductResponseDTO entityList);
}
