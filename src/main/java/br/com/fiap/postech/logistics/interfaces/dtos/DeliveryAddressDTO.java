package br.com.fiap.postech.logistics.interfaces.dtos;

import java.util.UUID;

public record DeliveryAddressDTO(
        UUID id,
        String street,
        String number,
        String complement,
        String district,
        String city,
        String state,
        String country,
        String postalCode
) {}
