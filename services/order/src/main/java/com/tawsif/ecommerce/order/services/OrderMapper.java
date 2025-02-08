package com.tawsif.ecommerce.order.services;

import com.tawsif.ecommerce.order.models.Order;
import com.tawsif.ecommerce.order.models.OrderRequest;
import com.tawsif.ecommerce.order.models.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
//        return Order.builder()
//                .id(request.id())
//                .customerId(request.customerId())
//                .reference(request.reference())
//                .totalAmount(request.amount())
//                .paymentMethod(request.paymentMethod())
//                .build();
        Order order = new Order();
        order.setId(request.id());
        order.setCustomerId(request.customerId());
        order.setReference(request.reference());
        order.setTotalAmount(request.amount());
        order.setPaymentMethod(request.paymentMethod());
        return order;
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
