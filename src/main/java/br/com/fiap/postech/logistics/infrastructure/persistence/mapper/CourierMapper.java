package br.com.fiap.postech.logistics.infrastructure.persistence.mapper;

import br.com.fiap.postech.logistics.domain.factory.CourierFactory;
import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.CourierEntity;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {

    private final CourierFactory courierFactory;

    public CourierMapper(CourierFactory courierFactory){
        this.courierFactory = courierFactory;
    }

    public Courier toDomain(CourierEntity entity) {
        if (entity == null) return null;
        return courierFactory.create(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.isActive()
        );
    }

    public CourierEntity toEntity(Courier courier) {
        if (courier == null) return null;
        return new CourierEntity(
                courier.getId(),
                courier.getName(),
                courier.getPhoneNumber(),
                courier.isActive()
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
