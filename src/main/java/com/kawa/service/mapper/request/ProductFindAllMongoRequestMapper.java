package com.kawa.service.mapper.request;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.request.mongo.ProductFindAllMongoRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductFindAllMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductFindAllMongoRequestMapper {}
