package com.kawa.service.dto.response.mongo;

import com.kawa.domain.bean.Order;

public class OrderFindAllMongoResponseDTO extends FindAllMongoResponseDTO<Order> {

    @Override
    public String toString() {
        return "OrderFindAllMongoResponseDTO{" + "documents=" + documents + '}';
    }
}
