package com.kawa.service.dto.request.mongo;

public class OrderFindAllMongoRequestDTO extends FindAllMongoRequestDTO {

    public OrderFindAllMongoRequestDTO() {
        this.collection = "orders";
    }

    @Override
    public String toString() {
        return (
            "OrderFindAllMongoRequestDTO{" +
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
