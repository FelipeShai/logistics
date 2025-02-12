package br.com.fiap.postech.logistics.interfaces.gateway.impl;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryEntity;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.DeliveryEntityMapper;
import br.com.fiap.postech.logistics.infrastructure.persistence.repository.DeliveryRepository;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveryJpaGateway implements DeliveryGateway {
    private final DeliveryRepository repository;
    private final DeliveryEntityMapper mapper;

    @Override
    @Transactional
    public Delivery save(Delivery delivery) {
        DeliveryEntity entity = repository.findById(delivery.getId())
                .orElse(null);
        if (entity == null) {
            entity = mapper.toEntity(delivery);
        } else {
            mapper.updateEntityFromDomain(entity, delivery);
        }
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Delivery> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
