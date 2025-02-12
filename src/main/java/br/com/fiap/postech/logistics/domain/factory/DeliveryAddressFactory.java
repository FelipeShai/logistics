package br.com.fiap.postech.logistics.domain.factory;

import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;

import java.util.UUID;

public interface DeliveryAddressFactory {
    DeliveryAddress create(UUID id, String street, String number, String complement,
                           String district, String city, String state,
                           String country, String postalCode);
}
