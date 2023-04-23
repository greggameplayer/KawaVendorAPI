package com.kawa.service.mapper.response;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.response.mongo.ProductFindAllMongoResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductFindAllMongoResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductFindAllMongoResponseMapper {}
