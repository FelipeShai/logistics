package br.com.fiap.postech.logistics.interfaces.dtos;

import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DeliveryResponseDTO(UUID id, DeliveryStatus status, DeliveryAddressDTO address, LocalDateTime createdAt, CourierResponseDTO courier) {}
