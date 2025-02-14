package br.com.fiap.postech.logistics.domain.events;

public record AddressEvent(
        String street,
        String number,
        String complement,
        String district,
        String city,
        String state,
        String country,
        String postalCode
) {}
