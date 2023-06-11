package com.kawa.service.mapper.response;

import com.kawa.service.dto.response.OrderResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.OrderFindAllMongoResponseDTO;
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

    @Mapping(target = "orders", source = "documents")
    OrderResponseDTO toOrderEntity(OrderFindAllMongoResponseDTO dtoList);

    @Mapping(target = "documents", source = "orders")
    OrderFindAllMongoResponseDTO toOrderDto(OrderResponseDTO entityList);
}
