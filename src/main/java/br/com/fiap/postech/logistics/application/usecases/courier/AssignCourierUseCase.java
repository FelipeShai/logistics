package br.com.fiap.postech.logistics.application.usecases.courier;

import br.com.fiap.postech.logistics.domain.model.Delivery;

import java.util.UUID;

public interface AssignCourierUseCase {
    Delivery execute(UUID deliveryId, UUID courierId);
}
