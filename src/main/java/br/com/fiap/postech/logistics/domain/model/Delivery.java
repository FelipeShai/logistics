package br.com.fiap.postech.logistics.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Delivery {

    private final UUID id;
    private final UUID orderId;
    private final UUID customerId;
    private Courier courier;
    private DeliveryStatus status;
    private DeliveryAddress address;
    private final LocalDateTime createdAt;
    private LocalDateTime deliveredAt;

    public Delivery(UUID orderId, UUID customerId, DeliveryAddress address) {
        this(null, orderId, customerId, null, DeliveryStatus.PENDING, address, null, null);
    }

    public Delivery(UUID orderId, UUID customerId, DeliveryAddress address, Courier courier) {
        this(null, orderId, customerId, null, DeliveryStatus.PENDING, address, null, null);
    }

    public Delivery(UUID id, UUID orderId, UUID customerId, Courier courier,
                    DeliveryStatus status, DeliveryAddress address,
                    LocalDateTime createdAt, LocalDateTime deliveredAt) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.courier = courier;
        this.status = status;
        this.address = address;
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;
    }

    public UUID getId() { return id; }
    public UUID getOrderId() { return orderId; }
    public UUID getCustomerId() { return customerId; }
    public Courier getCourier() { return courier; }
    public DeliveryAddress getAddress() { return address; }
    public DeliveryStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }

    public void assignCourier(Courier courier) {
        if (this.status != DeliveryStatus.PENDING) {
            throw new IllegalStateException("Only PENDING deliveries can be assigned to a courier.");
        }
        this.courier = courier;
        this.status = DeliveryStatus.IN_TRANSIT;
    }

    public void markAsDelivered() {
        if (this.status != DeliveryStatus.IN_TRANSIT) {
            throw new IllegalStateException("Only IN_TRANSIT deliveries can be marked as delivered.");
        }
        this.status = DeliveryStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
    }

    public void updateStatus(DeliveryStatus newStatus) {
        if (this.status == DeliveryStatus.DELIVERED) {
            throw new IllegalStateException("Cannot change status of a delivered order.");
        }
        this.status = newStatus;
    }

    public void updateFrom(Delivery updated) {
        if (updated == null) {
            throw new IllegalArgumentException("Updated delivery cannot be null");
        }

        if (this.status.canBeUpdatedTo(updated.status)) {
            this.status = updated.status;
        }

        if (!this.address.equals(updated.address)) {
            this.address = updated.address;
        }
    }

}
