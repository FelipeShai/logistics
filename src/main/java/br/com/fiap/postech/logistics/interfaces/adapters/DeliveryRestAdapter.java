package br.com.fiap.postech.logistics.interfaces.adapters;

import br.com.fiap.postech.logistics.application.exception.custom.InvalidInputException;
import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.factory.DeliveryFactory;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.DeliveryAddressMapper;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryRequestDTO;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class DeliveryRestAdapter {

    private final DeliveryFactory deliveryFactory;
    private final DeliveryAddressFactory deliveryAddressFactory;
    private final DeliveryAddressMapper deliveryAddressMapper;

    public DeliveryRestAdapter(DeliveryFactory deliveryFactory, DeliveryAddressFactory deliveryAddressFactory, DeliveryAddressMapper deliveryAddressMapper) {
        this.deliveryFactory = deliveryFactory;
        this.deliveryAddressFactory = deliveryAddressFactory;
        this.deliveryAddressMapper = deliveryAddressMapper;
    }

    public Delivery toDomain(DeliveryRequestDTO requestDTO) {
            UUID orderId = parseUUID(requestDTO.orderId(), "orderId");
            UUID customerId = parseUUID(requestDTO.customerId(), "customerId");
            DeliveryAddress address = deliveryAddressFactory.createFromDTO(requestDTO.address());
            return deliveryFactory.create(orderId, customerId, address);
    }

    private UUID parseUUID(String uuidStr, String fieldName) {
        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            var exception = new InvalidInputException("Invalid " + fieldName + " format. Use a standard 36-character UUID.");
            log.error("Class: {}, Method: {}, Message: {}", "DeliveryRestAdapter", "parseUUID", exception.getMessage());
            throw exception;
        }
    }

    public DeliveryResponseDTO toResponse(Delivery saved) {
        return new DeliveryResponseDTO(saved.getId(), saved.getStatus(), deliveryAddressMapper.toDTO(saved.getAddress()), saved.getCreatedAt());
    }
}
