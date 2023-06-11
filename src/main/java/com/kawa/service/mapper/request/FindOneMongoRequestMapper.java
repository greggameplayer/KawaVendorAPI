package com.kawa.service.mapper.request;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.request.mongo.OrderFindOneMongoRequestDTO;
import com.kawa.service.dto.request.mongo.ProductFindOneMongoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the id of the entity {@link Product} and its Mongo DTO {@link ProductFindOneMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface FindOneMongoRequestMapper {
    @Mapping(target = "filterId", source = "id")
    ProductFindOneMongoRequestDTO toDto(String id);

    @Mapping(target = "filterId", source = "id")
    OrderFindOneMongoRequestDTO toOrderDto(String id);
}
