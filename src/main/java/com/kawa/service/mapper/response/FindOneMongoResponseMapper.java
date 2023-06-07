package com.kawa.service.mapper.response;

import com.kawa.domain.bean.Product;
import com.kawa.service.dto.response.ProductResponseDTO;
import com.kawa.service.dto.response.mongo.ProductFindOneMongoResponseDTO;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the Response DTO {@link ProductResponseDTO} and its Mongo DTO {@link ProductFindOneMongoResponseDTO}.
 */
@Mapper(componentModel = "spring", imports = { Arrays.class })
public interface FindOneMongoResponseMapper {
    @Mapping(target = "products", expression = "java(toEntityList(dto.getDocument()))")
    ProductResponseDTO toEntity(ProductFindOneMongoResponseDTO dto);

    @Mapping(target = "document", expression = "java(entityList.getProducts().get(0))")
    ProductFindOneMongoResponseDTO toDto(ProductResponseDTO entityList);

    default List<Product> toEntityList(Product product) {
        return (product.isNull()) ? Collections.emptyList() : Collections.singletonList(product);
    }
}
