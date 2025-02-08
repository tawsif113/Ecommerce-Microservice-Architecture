package com.tawsif.ecommerce.payment;

import com.tawsif.ecommerce.customer.CustomerResponse;
import com.tawsif.ecommerce.order.models.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
