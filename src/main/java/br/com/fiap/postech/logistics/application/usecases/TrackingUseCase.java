package br.com.fiap.postech.logistics.application.usecases;
import java.util.UUID;

public interface TrackingUseCase {
    void updateLocation(UUID deliveryId, Double latitude, Double longitude);
}

