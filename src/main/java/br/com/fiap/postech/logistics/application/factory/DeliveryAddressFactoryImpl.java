package br.com.fiap.postech.logistics.application.factory;

import br.com.fiap.postech.logistics.domain.events.AddressEvent;
import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryAddressDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeliveryAddressFactoryImpl implements DeliveryAddressFactory {

    public DeliveryAddress create(UUID id, String street, String number, String complement,
                                  String district, String city, String state,
                                  String country, String postalCode) {
        return new DeliveryAddress(id, street, number, complement, district, city, state, country, postalCode);
    }

    public DeliveryAddress fromEvent(AddressEvent event) {
        return new DeliveryAddress(
                UUID.randomUUID(),
                event.street(),
                event.number(),
                event.complement(),
                event.district(),
                event.city(),
                event.state(),
                event.country(),
                event.postalCode()
        );
    }
    public DeliveryAddress fromDTO(DeliveryAddressDTO dto) {
        return new DeliveryAddress(
                UUID.randomUUID(),
                dto.street(),
                dto.number(),
                dto.complement(),
                dto.district(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalCode()
        );
    }

    public DeliveryAddressDTO toDTO(DeliveryAddress address) {
        return new DeliveryAddressDTO(
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getDistrict(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode()
        );
    }
}
