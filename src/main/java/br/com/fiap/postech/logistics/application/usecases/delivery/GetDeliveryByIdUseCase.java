package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;

import java.util.Optional;
import java.util.UUID;

public interface GetDeliveryByIdUseCase {
    Optional<Delivery> execute(UUID id);
}
