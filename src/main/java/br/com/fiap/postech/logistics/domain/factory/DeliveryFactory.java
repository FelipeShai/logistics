package br.com.fiap.postech.logistics.domain.factory;

import br.com.fiap.postech.logistics.domain.events.AddressEvent;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DeliveryFactory {
    Delivery create(UUID orderId, UUID customerId, DeliveryAddress address);

    Delivery create(UUID id, UUID orderId, UUID customerId, UUID courierId,
                    DeliveryStatus status, DeliveryAddress address,
                    LocalDateTime createdAt, LocalDateTime deliveredAt);

}
