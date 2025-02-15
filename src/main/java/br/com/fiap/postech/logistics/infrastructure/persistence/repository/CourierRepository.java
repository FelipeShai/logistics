package br.com.fiap.postech.logistics.infrastructure.persistence.repository;

import br.com.fiap.postech.logistics.infrastructure.persistence.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, UUID> {
}

