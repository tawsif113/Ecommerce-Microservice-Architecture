package com.tawsif.ecommerce.order.repositories;

import com.tawsif.ecommerce.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
