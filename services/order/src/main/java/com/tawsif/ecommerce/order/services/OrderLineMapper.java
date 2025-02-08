package com.tawsif.ecommerce.order.services;

import com.tawsif.ecommerce.order.models.Order;
import com.tawsif.ecommerce.orderline.OrderLine;
import com.tawsif.ecommerce.orderline.OrderLineRequest;
import com.tawsif.ecommerce.orderline.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
//        return OrderLine.builder()
//                .id(orderLineRequest.id())
//                .productId(orderLineRequest.productId())
//                .quantity(orderLineRequest.quantity())
//                .order(
//                        Order.builder()
//                                .id(orderLineRequest.orderId())
//                                .build()
//                )
//                .build();
        OrderLine orderLine = new OrderLine();
        orderLine.setId(orderLineRequest.id());
        orderLine.setProductId(orderLineRequest.productId());
        orderLine.setQuantity(orderLineRequest.quantity());
        Order order = new Order();
        order.setId(orderLineRequest.orderId());
        orderLine.setOrder(order);
        return orderLine;
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
