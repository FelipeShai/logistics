package br.com.fiap.postech.logistics.interfaces.dtos;

import jakarta.validation.constraints.NotNull;

public record DeliveryRequestDTO(
        @NotNull(message = "OrderId is required")
        String orderId,

        @NotNull(message = "CustomerId is required")
        String customerId,

        @NotNull(message = "Address is required")
        DeliveryAddressDTO address
) {}
