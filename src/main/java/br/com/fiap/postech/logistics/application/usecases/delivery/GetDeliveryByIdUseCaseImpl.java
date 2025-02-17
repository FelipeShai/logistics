package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetDeliveryByIdUseCaseImpl {

    private final DeliveryGateway deliveryGateway;

    public GetDeliveryByIdUseCaseImpl(DeliveryGateway deliveryGateway) {
        this.deliveryGateway = deliveryGateway;
    }

    public Delivery execute(UUID deliveryId) {
        return deliveryGateway.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));
    }
}
