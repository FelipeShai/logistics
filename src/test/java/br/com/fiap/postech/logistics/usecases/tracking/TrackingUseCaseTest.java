package br.com.fiap.postech.logistics.usecases.tracking;

import br.com.fiap.postech.logistics.application.usecases.TrackingUseCaseImpl;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackingUseCaseTest {

    @Mock
    private DeliveryGateway deliveryGateway;

    @InjectMocks
    private TrackingUseCaseImpl  trackingUseCase;

    @Test
    void shouldUpdateLocationWhenDeliveryFound() {
        UUID deliveryId = UUID.randomUUID();
        Delivery mockDelivery = mock(Delivery.class);

        when(deliveryGateway.findById(deliveryId)).thenReturn(Optional.of(mockDelivery));

        trackingUseCase.updateLocation(deliveryId, 10.0, 20.0);

        verify(mockDelivery).updateTracking(10.0, 20.0);
        verify(deliveryGateway).save(mockDelivery);
    }

    @Test
    void shouldThrowExceptionWhenDeliveryNotFound() {
        UUID invalidId = UUID.randomUUID();
        when(deliveryGateway.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                trackingUseCase.updateLocation(invalidId, 10.0, 20.0)
        );
    }
}
