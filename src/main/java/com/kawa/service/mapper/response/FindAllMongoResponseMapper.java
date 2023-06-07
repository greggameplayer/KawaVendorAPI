package com.kawa.service.mapper.response;

import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindAllMongoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the Response DTO {@link ProductResponseDTO} and its Mongo DTO {@link ProductFindAllMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface FindAllMongoResponseMapper {
    @Mapping(target = "products", source = "documents")
    ProductResponseDTO toEntity(ProductFindAllMongoResponseDTO dtoList);

    @Mapping(target = "documents", source = "products")
    ProductFindAllMongoResponseDTO toDto(ProductResponseDTO entityList);
}
