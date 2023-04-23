package com.kawa.service.mapper.request;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.request.ProductRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductRequestMapper {}
