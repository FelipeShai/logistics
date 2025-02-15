package br.com.fiap.postech.logistics.interfaces.adapters;

import br.com.fiap.postech.logistics.domain.factory.CourierFactory;
import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierRequestDTO;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourierRestAdapter {

    CourierFactory courierFactory;

    public CourierRestAdapter(CourierFactory courierFactory) {
        this.courierFactory = courierFactory;
    }

    public Courier toDomain(CourierRequestDTO dto) {
        return courierFactory.create(
                dto.name(),
                dto.phoneNumber(),
                dto.active()
        );
    }

    public CourierResponseDTO toResponse(Courier saved) {
        return new CourierResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getPhoneNumber(),
                saved.isActive()
        );
    }
}
