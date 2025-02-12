package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetDeliveryByIdUseCaseImpl implements GetDeliveryByIdUseCase {

    private final DeliveryGateway deliveryGateway;

    public GetDeliveryByIdUseCaseImpl(DeliveryGateway deliveryGateway) {
        this.deliveryGateway = deliveryGateway;
    }

    @Override
    public Optional<Delivery> execute(UUID id) {
        return deliveryGateway.findById(id);
    }
}
