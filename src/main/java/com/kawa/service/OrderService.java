package com.kawa.service;

import com.kawa.service.dto.request.OrderInsertRequestDTO;
import com.kawa.service.dto.response.InsertResponseDTO;
import com.kawa.service.dto.response.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO getOrder(String id);

    OrderResponseDTO getOrders();

    InsertResponseDTO insertOrder(OrderInsertRequestDTO requestDTO);
}
