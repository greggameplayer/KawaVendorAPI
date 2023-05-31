package com.kawa.service;

import com.kawa.domain.bean.ProductWithoutId;
import com.kawa.service.dto.response.ProductInsertResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO getProduct(String id);

    ProductResponseDTO getProducts();

    ProductInsertResponseDTO insertProduct(ProductWithoutId product);
}
