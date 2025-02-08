package com.tawsif.ecommerce.kafka.customer;

import com.tawsif.ecommerce.kafka.order.Customer;

public record CustomerEvent(
        String eventType,
        String timestamp,
        Customer customer
) {
}
