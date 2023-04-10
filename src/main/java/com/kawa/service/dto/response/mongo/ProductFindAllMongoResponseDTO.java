package com.kawa.service.dto.response.mongo;

import com.kawa.domain.bean.Product;

import java.util.List;

public class ProductFindAllMongoResponseDTO {

    private List<Product> documents;

    public List<Product> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Product> documents) {
        this.documents = documents;
    }
}
