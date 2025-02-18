package br.com.fiap.postech.logistics.application.usecases;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackingUseCaseImpl implements TrackingUseCase {

    private final DeliveryGateway deliveryGateway;

    @Override
    public void updateLocation(UUID deliveryId, Double latitude, Double longitude) {
        Delivery delivery = deliveryGateway.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

        delivery.updateTracking(latitude, longitude);

        deliveryGateway.save(delivery);
    }
}

