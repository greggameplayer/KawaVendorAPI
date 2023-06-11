package com.kawa.service.dto.response;

import com.kawa.domain.bean.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrderResponseDTO {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderResponseDTO)) return false;
        OrderResponseDTO that = (OrderResponseDTO) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" + "orders=" + orders + '}';
    }
}
