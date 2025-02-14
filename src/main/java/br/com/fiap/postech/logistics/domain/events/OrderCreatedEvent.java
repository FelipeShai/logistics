package br.com.fiap.postech.logistics.domain.events;

import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        UUID customerId,
        AddressEvent address
) {}
