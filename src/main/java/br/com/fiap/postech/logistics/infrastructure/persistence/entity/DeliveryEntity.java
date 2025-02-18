package br.com.fiap.postech.logistics.infrastructure.persistence.entity;

import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "deliveries",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_order_id", columnNames = {"order_id"})
        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private UUID customerId;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private DeliveryAddressEntity address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;


    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime deliveredAt;

    public static DeliveryEntity create(
            UUID id,
            UUID orderId,
            UUID customerId,
            CourierEntity courier,
            DeliveryStatus status,
            DeliveryAddressEntity address,
            Double latitude,
            Double longitude,
            LocalDateTime createdAt,
            LocalDateTime deliveredAt) {

        return new DeliveryEntity(
                id,
                orderId,
                customerId,
                courier,
                status,
                address,
                latitude,
                longitude,
                createdAt,
                deliveredAt
        );
    }

    public void assignCourier(CourierEntity courier) {
        this.courier = courier;
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
