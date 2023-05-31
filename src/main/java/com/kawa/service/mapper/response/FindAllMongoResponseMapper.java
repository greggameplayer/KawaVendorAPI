package com.kawa.service.mapper.response;

import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindAllMongoResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the Response DTO {@link ProductResponseDTO} and its Mongo DTO {@link ProductFindAllMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface FindAllMongoResponseMapper {
    ProductResponseDTO toEntity(ProductFindAllMongoResponseDTO dtoList);

    ProductFindAllMongoResponseDTO toDto(ProductResponseDTO entityList);
}
