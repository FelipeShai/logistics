package br.com.fiap.postech.logistics.infrastructure.persistence.repository;

import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, UUID> {

    Optional<DeliveryEntity> findByOrderId(UUID orderId);

    List<DeliveryEntity> findByAddressPostalCodeAndStatus(String zip, DeliveryStatus status);
}
