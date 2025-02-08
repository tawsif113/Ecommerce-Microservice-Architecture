package com.tawsif.ecommerce.kafka;

import com.tawsif.ecommerce.customer.CustomerResponse;
import com.tawsif.ecommerce.order.models.PaymentMethod;
import com.tawsif.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
