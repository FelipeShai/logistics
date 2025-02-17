package br.com.fiap.postech.logistics.interfaces.gateway.database;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryGateway {
    Delivery save(Delivery delivery);
    Optional<Delivery> findByOrderId(UUID orderId);
    Optional<Delivery> findById(UUID id);
    void deleteById(UUID id);
    List<DeliveryEntity> findByAddressPostalCodeAndStatus(String zip, DeliveryStatus status);

}
