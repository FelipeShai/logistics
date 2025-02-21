package br.com.fiap.postech.logistics.interfaces.dtos;

import java.util.UUID;

public record DeliveryAssignmentDTO(UUID courierId, UUID deliveryId) { }
