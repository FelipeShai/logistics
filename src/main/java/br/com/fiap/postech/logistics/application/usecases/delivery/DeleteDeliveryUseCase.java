package br.com.fiap.postech.logistics.application.usecases.delivery;

import java.util.UUID;

public interface DeleteDeliveryUseCase {
    void execute(UUID id);
}
