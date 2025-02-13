package br.com.fiap.postech.logistics.domain.events;

import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        UUID customerId,
        Address address
) {}

record Address(
        String street,
        String number,
        String complement,
        String district,
        String city,
        String state,
        String country,
        String postalCode
) {}