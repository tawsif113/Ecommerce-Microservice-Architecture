package com.tawsif.ecommerce.models;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Product id is mandatory")
        Integer productId,

        @NotNull(message = "Quantity is mandatory")
        double quantity

) {
}
