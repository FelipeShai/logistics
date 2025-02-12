package br.com.fiap.postech.logistics.infrastructure.persistence.entity;

import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deliveries")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private UUID customerId;

    @Column
    private UUID courierId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private DeliveryAddressEntity address;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime deliveredAt;

    public static DeliveryEntity create(UUID id, UUID orderId, UUID customerId, UUID courierId,
                                        DeliveryStatus status, DeliveryAddressEntity address,
                                        LocalDateTime createdAt, LocalDateTime deliveredAt) {
        return new DeliveryEntity(id, orderId, customerId, courierId, status, address, createdAt, deliveredAt);
    }

    public void assignCourier(UUID courierId) {
        this.courierId = courierId;
    }

    public void updateStatus(DeliveryStatus status) {
        this.status = status;
    }

    public void markAsDelivered(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public void updateAddress(DeliveryAddressEntity address) {
        this.address = address;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
