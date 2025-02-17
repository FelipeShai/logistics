package br.com.fiap.postech.logistics.domain.factory;

import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierRequestDTO;

import java.util.Optional;
import java.util.UUID;

public interface CourierFactory {

    Courier create(UUID id, String name, String phoneNumber, boolean active);
    Courier create(String name, String phoneNumber, boolean active);
    Courier createFromDTO(CourierRequestDTO dto);

}
