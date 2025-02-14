package br.com.fiap.postech.logistics.infrastructure.persistence.mapper;

import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryAddressEntity;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressMapper {

    private final DeliveryAddressFactory deliveryAddressFactory;

    public DeliveryAddressMapper(DeliveryAddressFactory deliveryAddressFactory){
        this.deliveryAddressFactory = deliveryAddressFactory;
    }

    public DeliveryAddress toDomain(DeliveryAddressEntity entity) {
        if (entity == null) return null;
        return deliveryAddressFactory.create(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement(),
                entity.getDistrict(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode()
        );
    }

    public DeliveryAddressEntity toEntity(DeliveryAddress address) {
        if (address == null) return null;
        return DeliveryAddressEntity.create(
                null,
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
