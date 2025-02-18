package br.com.fiap.postech.logistics.application.usecases;

import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import java.util.UUID;

public interface UpdateDeliveryStatusUseCase {
    void execute(UUID deliveryId, DeliveryStatus newStatus);
}
