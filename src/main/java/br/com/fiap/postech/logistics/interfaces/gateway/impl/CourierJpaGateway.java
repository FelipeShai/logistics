package br.com.fiap.postech.logistics.interfaces.gateway.impl;

import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.CourierMapper;
import br.com.fiap.postech.logistics.infrastructure.persistence.repository.CourierRepository;
import br.com.fiap.postech.logistics.interfaces.gateway.database.CourierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourierJpaGateway implements CourierGateway {

    private final CourierRepository repository;
    private final CourierMapper mapper;

    @Override
    public Courier save(Courier courier) {
        var entity = mapper.toEntity(courier);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Courier> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Courier> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
