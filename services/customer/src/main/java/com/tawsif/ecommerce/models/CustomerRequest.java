package com.tawsif.ecommerce.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @NotNull(message = "Address is required")
        Address address
) {


}
