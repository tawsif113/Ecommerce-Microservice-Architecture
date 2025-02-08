package com.tawsif.ecommerce.order.models;

import com.tawsif.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message ="Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer id is required")
        @NotBlank(message = "Customer   id is required")
        @NotEmpty(message = "Customer id is required")
        String customerId,

        @NotEmpty(message = "Products are required")
        List<PurchaseRequest> products
) {
}
