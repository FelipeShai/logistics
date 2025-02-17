package br.com.fiap.postech.logistics.interfaces.dtos;

import java.util.UUID;

public record DeliveryAssignmentRequest(UUID courierId, UUID deliveryId) { }
