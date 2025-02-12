package br.com.fiap.postech.logistics.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Delivery {

    private final UUID id;
    private final UUID orderId;
    private final UUID customerId;
    private UUID courierId;
    private DeliveryStatus status;
    private final DeliveryAddress address;
    private final LocalDateTime createdAt;
    private LocalDateTime deliveredAt;

    // Construtor sem ID (JPA gera automaticamente)
    public Delivery(UUID orderId, UUID customerId, DeliveryAddress address) {
        this(null, orderId, customerId, null, DeliveryStatus.PENDING, address, null, null);
    }

    // Construtor completo
    public Delivery(UUID id, UUID orderId, UUID customerId, UUID courierId,
                    DeliveryStatus status, DeliveryAddress address,
                    LocalDateTime createdAt, LocalDateTime deliveredAt) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.courierId = courierId;
        this.status = status;
        this.address = address;
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;
    }

    public UUID getId() { return id; }
    public UUID getOrderId() { return orderId; }
    public UUID getCustomerId() { return customerId; }
    public UUID getCourierId() { return courierId; }
    public DeliveryAddress getAddress() { return address; }
    public DeliveryStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }

    public void assignCourier(UUID courierId) {
        if (this.status != DeliveryStatus.PENDING) {
            throw new IllegalStateException("Only PENDING deliveries can be assigned to a courier.");
        }
        this.courierId = courierId;
        this.status = DeliveryStatus.IN_TRANSIT;
    }

    public void markAsDelivered() {
        if (this.status != DeliveryStatus.IN_TRANSIT) {
            throw new IllegalStateException("Only IN_TRANSIT deliveries can be marked as delivered.");
        }
        this.status = DeliveryStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
    }
}
