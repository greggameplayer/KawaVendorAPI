package com.kawa.service.mapper.request;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.request.mongo.ProductInsertMongoRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductInsertMongoRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductInsertMongoRequestMapper {}
