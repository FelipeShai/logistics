package br.com.fiap.postech.logistics.application.factory;

import br.com.fiap.postech.logistics.domain.factory.DeliveryFactory;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DeliveryFactoryImpl implements DeliveryFactory {

    @Override
    public Delivery create(UUID orderId, UUID customerId, DeliveryAddress address) {
        return new Delivery(orderId, customerId, address);
    }

    @Override
    public Delivery create(UUID id, UUID orderId, UUID customerId, UUID courierId,
                           DeliveryStatus status, DeliveryAddress address,
                           LocalDateTime createdAt, LocalDateTime deliveredAt) {
        return new Delivery(id, orderId, customerId, courierId, status, address, createdAt, deliveredAt);
    }
}
