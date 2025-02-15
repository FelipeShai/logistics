package br.com.fiap.postech.logistics.interfaces.dtos;

import java.util.UUID;

public record CourierRequestDTO(
        UUID id,
        String name,
        String phoneNumber,
        boolean active
) {}
