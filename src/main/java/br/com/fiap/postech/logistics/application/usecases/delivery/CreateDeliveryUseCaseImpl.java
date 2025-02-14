package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateDeliveryUseCaseImpl implements CreateDeliveryUseCase {

    private final DeliveryGateway deliveryGateway;

    public CreateDeliveryUseCaseImpl(DeliveryGateway deliveryGateway) {
        this.deliveryGateway = deliveryGateway;
    }

    @Override
    public Delivery execute(Delivery delivery) {
        return deliveryGateway.findByOrderId(delivery.getOrderId())
                .map(existingDelivery -> updateExistingDelivery(existingDelivery, delivery))
                .orElseGet(() -> deliveryGateway.save(delivery));
    }

    private Delivery updateExistingDelivery(Delivery existing, Delivery updated) {
        existing.updateFrom(updated);
        return deliveryGateway.save(existing);
    }
}

