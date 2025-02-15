package br.com.fiap.postech.logistics.interfaces.dtos;

import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliveryResponseDTO(UUID id, DeliveryStatus status, DeliveryAddressDTO address, LocalDateTime createdAt) {}
