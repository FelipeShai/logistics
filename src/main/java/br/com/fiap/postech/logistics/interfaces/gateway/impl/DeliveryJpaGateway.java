package br.com.fiap.postech.logistics.interfaces.gateway.impl;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryEntity;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.DeliveryEntityMapper;
import br.com.fiap.postech.logistics.infrastructure.persistence.repository.DeliveryRepository;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryJpaGateway implements DeliveryGateway {
    private final DeliveryRepository repository;
    private final DeliveryEntityMapper mapper;

    public Delivery save(Delivery delivery) {

        logSavingDelivery(delivery.getOrderId());

        DeliveryEntity entity = mapper.toEntity(delivery);
        DeliveryEntity savedEntity = repository.save(entity);

        logDeliverySaved(savedEntity.getId());
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Delivery> findByOrderId(UUID orderId) {
        return repository.findByOrderId(orderId).map(mapper::toDomain);
    }

    public Optional<Delivery> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    private void logSavingDelivery(UUID orderId) {
        log.info("Class={}, Method={}, Message={}", "DeliveryJpaGateway", "save", "Saving delivery with Order ID: " + orderId);
    }
    private void logDeliverySaved(UUID id) {
        log.info("Class={}, Method={}, Message={}", "DeliveryJpaGateway", "save", "Delivery saved successfully with ID: " + id);
    }
}
