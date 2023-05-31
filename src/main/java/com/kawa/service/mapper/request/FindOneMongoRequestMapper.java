package com.kawa.service.mapper.request;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the id of the entity {@link Product} and its Mongo DTO {@link ProductFindOneMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface FindOneMongoRequestMapper {
    ProductFindOneMongoRequestDTO toDto(String id);
}
