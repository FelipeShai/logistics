package br.com.fiap.postech.logistics.interfaces.gateway.database;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryGateway {
    Delivery save(Delivery delivery);
    Optional<Delivery> findById(UUID id);
    void deleteById(UUID id);
}
