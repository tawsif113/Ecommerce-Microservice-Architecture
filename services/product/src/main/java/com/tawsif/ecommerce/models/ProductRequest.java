package com.tawsif.ecommerce.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Description is mandatory")
        String description,
        @NotNull(message = "Name is mandatory")
        String name,
        @Positive(message = "Quantity should be positive")
        double availableQuantity,
        @Positive(message = "Price should be positive")
        BigDecimal price,
        @NotNull(message="Catagory can not be null")
        Integer categoryId
) {
}
