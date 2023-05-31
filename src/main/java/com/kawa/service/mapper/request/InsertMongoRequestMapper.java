package com.kawa.service.mapper.request;

import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.request.mongo.ProductInsertMongoRequestDTO;
import java.util.Date;
import org.mapstruct.Mapper;

/**
 * Mapper for the DTO {@link ProductInsertRequestDTO} and its Mongo DTO {@link ProductInsertMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsertMongoRequestMapper {
    ProductInsertMongoRequestDTO toDto(ProductInsertRequestDTO dto, Date createdAt);
}
