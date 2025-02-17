package br.com.fiap.postech.logistics.application.usecases.courier;

import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.CourierGateway;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssignCourierUseCaseImpl implements AssignCourierUseCase {

    private final CourierGateway courierGateway;
    private final DeliveryGateway deliveryGateway;

    public AssignCourierUseCaseImpl(CourierGateway courierGateway, DeliveryGateway deliveryGateway) {
        this.courierGateway = courierGateway;
        this.deliveryGateway = deliveryGateway;
    }

    public Delivery execute(UUID courierId, UUID deliveryId) {
        Courier courier = courierGateway.findById(courierId)
                .filter(Courier::isActive)
                .orElseThrow(() -> new IllegalStateException("Courier not found or not active."));

        Delivery delivery = deliveryGateway.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

        delivery.assignCourier(courier);

        return deliveryGateway.save(delivery);
    }
}
