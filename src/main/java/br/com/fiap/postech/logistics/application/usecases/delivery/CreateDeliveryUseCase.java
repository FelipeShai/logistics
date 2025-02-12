package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;

public interface CreateDeliveryUseCase {
    Delivery execute(Delivery delivery);
}
