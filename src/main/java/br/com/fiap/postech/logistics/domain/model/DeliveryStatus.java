package br.com.fiap.postech.logistics.domain.model;

public enum DeliveryStatus {
    PENDING,
    IN_TRANSIT,
    DELIVERED,
    CANCELED;

    public boolean canBeUpdatedTo(DeliveryStatus newStatus) {
        if (newStatus == null) {
            return false;
        }

        return switch (this) {
            case PENDING -> newStatus == IN_TRANSIT || newStatus == CANCELED;
            case IN_TRANSIT -> newStatus == DELIVERED || newStatus == CANCELED;
            case DELIVERED -> false; // Entrega concluída não pode ser alterada
            case CANCELED -> false; // Cancelamento é definitivo
            default -> false;
        };
    }
}
