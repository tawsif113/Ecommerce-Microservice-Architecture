package com.tawsif.ecommerce.orderline;

import com.tawsif.ecommerce.order.OrderLineMapper;
import com.tawsif.ecommerce.order.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(mapper::toOrderLineResponse)
                .toList();
    }
}
