package com.kawa.service.dto.response.mongo;

import com.kawa.domain.bean.Product;

public class ProductFindAllMongoResponseDTO extends FindAllMongoResponseDTO<Product> {

    @Override
    public String toString() {
        return "ProductFindAllMongoResponseDTO{" + "documents=" + documents + '}';
    }
}
