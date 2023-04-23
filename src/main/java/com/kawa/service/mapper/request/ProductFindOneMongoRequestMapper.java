package com.kawa.service.mapper.request;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductFindOneMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductFindOneMongoRequestMapper {
    ProductFindOneMongoRequestDTO toDto(String id);

    Product toEntity(ProductFindOneMongoRequestDTO dto);
}
