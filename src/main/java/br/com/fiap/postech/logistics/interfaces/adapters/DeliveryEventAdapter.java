package br.com.fiap.postech.logistics.interfaces.adapters;

import br.com.fiap.postech.logistics.application.usecases.delivery.CreateDeliveryUseCase;
import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.factory.DeliveryFactory;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventAdapter {
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final DeliveryAddressFactory deliveryAddressFactory;
    private final DeliveryFactory deliveryFactory;

    public DeliveryEventAdapter(CreateDeliveryUseCase createDeliveryUseCase,
                                DeliveryAddressFactory deliveryAddressFactory,
                                DeliveryFactory deliveryFactory) {
        this.createDeliveryUseCase = createDeliveryUseCase;
        this.deliveryAddressFactory = deliveryAddressFactory;
        this.deliveryFactory = deliveryFactory;
    }

    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        Delivery delivery = deliveryFactory.create(
                event.orderId(),
                event.customerId(),
                deliveryAddressFactory.createFromEvent(event.address())
        );
        createDeliveryUseCase.execute(delivery);
    }
}
