package com.kawa.service.dto.response.mongo;

import com.kawa.domain.bean.Product;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProductFindAllMongoResponseDTO {

    private List<Product> documents;

    public List<Product> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    public void setDocuments(List<Product> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFindAllMongoResponseDTO that = (ProductFindAllMongoResponseDTO) o;
        return Objects.equals(documents, that.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documents);
    }

    @Override
    public String toString() {
        return "ProductFindAllMongoResponseDTO{" + "documents=" + documents + '}';
    }
}
