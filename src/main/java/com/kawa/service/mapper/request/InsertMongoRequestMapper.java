package com.kawa.service.mapper.request;

import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.request.mongo.ProductInsertMongoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the DTO {@link ProductInsertRequestDTO} and its Mongo DTO {@link ProductInsertMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsertMongoRequestMapper {
    @Mapping(target = "documentCreatedAt", expression = "java(new java.util.Date())")
    @Mapping(target = "documentName", source = "name")
    @Mapping(target = "documentStock", source = "stock")
    @Mapping(target = "documentDetailsPrice", source = "detailsPrice")
    @Mapping(target = "documentDetailsDescription", source = "detailsDescription")
    @Mapping(target = "documentDetailsColor", source = "detailsColor")
    ProductInsertMongoRequestDTO toDto(ProductInsertRequestDTO dto);
}
