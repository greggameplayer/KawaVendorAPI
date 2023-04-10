package com.kawa.service.dto.response;

import com.kawa.domain.bean.Product;

import java.util.List;

public class ProductResponseDTO {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
