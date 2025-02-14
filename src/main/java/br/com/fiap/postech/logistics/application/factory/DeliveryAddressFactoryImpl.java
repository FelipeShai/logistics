package br.com.fiap.postech.logistics.application.factory;

import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeliveryAddressFactoryImpl implements DeliveryAddressFactory {

    public DeliveryAddress create(UUID id, String street, String number, String complement,
                                  String district, String city, String state,
                                  String country, String postalCode) {
        return new DeliveryAddress(id, street, number, complement, district, city, state, country, postalCode);
    }
}
