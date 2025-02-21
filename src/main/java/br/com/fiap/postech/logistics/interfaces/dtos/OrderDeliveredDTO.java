package br.com.fiap.postech.logistics.interfaces.dtos;

import java.util.UUID;

public record OrderDeliveredDTO(UUID orderId, String orderStatus) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID orderId;
        private String orderStatus = "DELIVERED";

        public Builder orderId(UUID orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderDeliveredDTO build() {
            return new OrderDeliveredDTO(orderId, orderStatus);
        }
    }
}
