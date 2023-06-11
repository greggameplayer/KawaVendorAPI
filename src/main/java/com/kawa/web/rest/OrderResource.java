package com.kawa.web.rest;

import com.kawa.service.OrderService;
import com.kawa.service.dto.request.OrderInsertRequestDTO;
import com.kawa.service.dto.response.InsertResponseDTO;
import com.kawa.service.dto.response.OrderResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer")
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/orders")
    public ResponseEntity<OrderResponseDTO> getOrders() {
        log.info("REST request to get all Orders");
        OrderResponseDTO result = orderService.getOrders();
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the orderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable String id) {
        log.info("REST request to get Order : {}", id);
        OrderResponseDTO result = orderService.getOrder(id);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code POST  /orders} : Create a new order.
     *
     * @param orderRequestDTO the orderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDTO, or with status {@code 400 (Bad Request)} if the order has already an ID.
     */
    @PostMapping("/orders")
    public ResponseEntity<InsertResponseDTO> createOrder(@Valid @RequestBody OrderInsertRequestDTO orderRequestDTO) {
        log.info("REST request to save Order : {}", orderRequestDTO);
        InsertResponseDTO result = orderService.insertOrder(orderRequestDTO);
        return ResponseEntity.ok().body(result);
    }
}
