package br.com.fiap.postech.logistics.interfaces.gateway.database;

import br.com.fiap.postech.logistics.domain.model.Courier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourierGateway {
    Courier save(Courier courier);
    Optional<Courier> findById(UUID id);
    List<Courier> findAll();
    void deleteById(UUID id);
}
