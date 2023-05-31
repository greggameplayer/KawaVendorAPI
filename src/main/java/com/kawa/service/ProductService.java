package com.kawa.service;

import com.kawa.service.dto.request.ProductInsertRequestDTO;
import com.kawa.service.dto.response.InsertResponseDTO;
import com.kawa.service.dto.response.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO getProduct(String id);

    ProductResponseDTO getProducts();

    InsertResponseDTO insertProduct(ProductInsertRequestDTO requestDTO);
}
