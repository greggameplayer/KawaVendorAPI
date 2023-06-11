package com.kawa.service.dto.request.mongo;

public class ProductFindAllMongoRequestDTO extends FindAllMongoRequestDTO {

    public ProductFindAllMongoRequestDTO() {
        this.collection = "products";
    }

    @Override
    public String toString() {
        return (
            "ProductFindAllMongoRequestDTO{" +
            "dataSource='" +
            dataSource +
            '\'' +
            ", database='" +
            database +
            '\'' +
            ", collection='" +
            collection +
            '\'' +
            '}'
        );
    }
}
