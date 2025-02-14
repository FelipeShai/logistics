package br.com.fiap.postech.logistics.interfaces.dtos;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.factory.DeliveryFactory;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class DeliveryRestAdapter {

    private final DeliveryFactory deliveryFactory;
    private final DeliveryAddressFactory deliveryAddressFactory;

    public Delivery toDomain(DeliveryRequestDTO requestDTO) {
        DeliveryAddress address = deliveryAddressFactory.fromDTO(requestDTO.address());
        return deliveryFactory.create(
                requestDTO.orderId(),
                requestDTO.customerId(),
                address
        );
    }

    public Delivery fromEvent(OrderCreatedEvent event) {
        return deliveryFactory.create(
                event.orderId(),
                event.customerId(),
                deliveryAddressFactory.fromEvent(event.address())
        );
    };

    public DeliveryResponseDTO toResponse(Delivery saved) {
        return new DeliveryResponseDTO(saved.getId(), saved.getStatus(), deliveryAddressFactory.toDTO(saved.getAddress()), saved.getCreatedAt());
    }

    public DeliveryRestAdapter(DeliveryFactory deliveryFactory, DeliveryAddressFactory deliveryAddressFactory) {
        this.deliveryFactory = deliveryFactory;
        this.deliveryAddressFactory = deliveryAddressFactory;
    }

    public record DeliveryRequestDTO(UUID orderId, UUID customerId, DeliveryAddressDTO address) {}
    public record DeliveryResponseDTO(UUID id, DeliveryStatus status, DeliveryAddressDTO address, LocalDateTime createdAt) {}
}
