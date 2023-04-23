package com.kawa.service.mapper.response;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.response.ProductResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductResponseMapper {}
