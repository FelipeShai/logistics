package br.com.fiap.postech.logistics.interfaces.dtos;

public record DeliveryAddressDTO(
        String street,
        String number,
        String complement,
        String district,
        String city,
        String state,
        String country,
        String postalCode
) {}
