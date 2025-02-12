package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteDeliveryUseCaseImpl implements DeleteDeliveryUseCase {

    private final DeliveryGateway deliveryGateway;

    public DeleteDeliveryUseCaseImpl(DeliveryGateway deliveryGateway) {
        this.deliveryGateway = deliveryGateway;
    }

    @Override
    public void execute(UUID id) {
        deliveryGateway.deleteById(id);
    }
}
