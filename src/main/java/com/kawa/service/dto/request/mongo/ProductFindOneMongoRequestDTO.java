package com.kawa.service.dto.request.mongo;

import java.util.Map;
import java.util.Objects;

public class ProductFindOneMongoRequestDTO extends FindOneMongoRequestDTO {

    public ProductFindOneMongoRequestDTO() {
        this.collection = "products";
    }

    private String filterId;

    @Override
    protected void unpackNested(Map<String, Object> filter) {
        this.filterId = (String) filter.get("_id");
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFindOneMongoRequestDTO)) return false;
        if (!super.equals(o)) return false;
        ProductFindOneMongoRequestDTO that = (ProductFindOneMongoRequestDTO) o;
        return Objects.equals(filterId, that.filterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filterId);
    }

    @Override
    public String toString() {
        return (
            "ProductFindOneMongoRequestDTO{" +
            "dataSource='" +
            dataSource +
            '\'' +
            ", database='" +
            database +
            '\'' +
            ", collection='" +
            collection +
            '\'' +
            ", filterId='" +
            filterId +
            '\'' +
            '}'
        );
    }
}
