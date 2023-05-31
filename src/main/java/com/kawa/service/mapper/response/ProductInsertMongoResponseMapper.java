package com.kawa.service.mapper.response;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.response.ProductInsertResponseDTO;
import com.kawa.service.dto.response.mongo.InsertMongoResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link InsertMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductInsertMongoResponseMapper {
    ProductInsertResponseDTO toEntity(InsertMongoResponseDTO dto);
}
