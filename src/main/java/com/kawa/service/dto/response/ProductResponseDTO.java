package com.kawa.service.dto.response;

import com.kawa.domain.bean.Product;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProductResponseDTO {

    private List<Product> products;

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDTO that = (ProductResponseDTO) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" + "products=" + products + '}';
    }
}
