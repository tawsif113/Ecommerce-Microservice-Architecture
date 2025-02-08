package com.tawsif.ecommerce.order.controllers;


import com.tawsif.ecommerce.order.services.OrderService;
import com.tawsif.ecommerce.order.models.OrderRequest;
import com.tawsif.ecommerce.order.models.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ){
        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId)
    {
        return ResponseEntity.ok(service.findById(orderId));
    }


}
