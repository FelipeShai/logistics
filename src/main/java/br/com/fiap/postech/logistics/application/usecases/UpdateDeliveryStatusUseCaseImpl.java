package br.com.fiap.postech.logistics.application.usecases;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateDeliveryStatusUseCaseImpl implements UpdateDeliveryStatusUseCase {

    private final DeliveryGateway deliveryGateway;

    @Override
    public void execute(UUID deliveryId, DeliveryStatus newStatus) {
        Delivery delivery = deliveryGateway.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

        if (!delivery.getStatus().canBeUpdatedTo(newStatus)) {
            throw new IllegalStateException("Invalid status transition.");
        }

        delivery.updateStatus(newStatus);
        deliveryGateway.save(delivery);
    }
}
