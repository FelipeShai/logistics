package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.application.exception.custom.DuplicateOrderIdException;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateDeliveryUseCaseImpl implements CreateDeliveryUseCase {

    private final DeliveryGateway deliveryGateway;

    public CreateDeliveryUseCaseImpl(DeliveryGateway deliveryGateway) {
        this.deliveryGateway = deliveryGateway;
    }

    @Override
    public Delivery execute(Delivery delivery) {
        try {
            return deliveryGateway.findByOrderId(delivery.getOrderId())
                    .map(existingDelivery -> updateExistingDelivery(existingDelivery, delivery))
                    .orElseGet(() -> deliveryGateway.save(delivery));
        } catch (DataIntegrityViolationException e) {
            log.error("Class = {}, Method = {}, Message: {}",
                    "CreateDeliveryUseCaseImpl", "execute", e.getMessage());
            throw new DuplicateOrderIdException(delivery.getOrderId().toString());
        }
    }

    private Delivery updateExistingDelivery(Delivery existing, Delivery updated) {
        existing.updateFrom(updated);
        return deliveryGateway.save(existing);
    }
}

