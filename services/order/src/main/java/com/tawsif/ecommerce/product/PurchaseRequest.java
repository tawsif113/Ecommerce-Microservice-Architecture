package com.tawsif.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

        @NotNull(message = "purchase id is mandatory")
        Integer productId,

        @Positive(message = "Quantity should be positive")
        double quantity

) {
}
