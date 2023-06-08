package com.kawa.service.dto.request;

import com.kawa.domain.bean.Product;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrderInsertRequestDTO {

    protected String customerId;

    protected List<String> productIds;

    public String getCustomerId() {
        return customerId;
    }

    public OrderInsertRequestDTO customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductIds() {
        return Collections.unmodifiableList(productIds);
    }

    public OrderInsertRequestDTO productIds(List<String> productIds) {
        this.productIds = productIds;
        return this;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderInsertRequestDTO)) return false;
        OrderInsertRequestDTO that = (OrderInsertRequestDTO) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(productIds, that.productIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productIds);
    }

    @Override
    public String toString() {
        return "OrderInsertRequestDTO{" + "customerId='" + customerId + '\'' + ", productIds=" + productIds + '}';
    }
}
