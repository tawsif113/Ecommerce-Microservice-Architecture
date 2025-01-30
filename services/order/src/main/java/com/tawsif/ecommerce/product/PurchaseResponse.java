package com.tawsif.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String name,
        double quantity,
        String description,
        BigDecimal price
) {
}
