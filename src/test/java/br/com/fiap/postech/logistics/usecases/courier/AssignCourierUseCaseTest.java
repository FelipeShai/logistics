package br.com.fiap.postech.logistics.usecases.courier;

import br.com.fiap.postech.logistics.application.usecases.courier.AssignCourierUseCaseImpl;
import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.CourierGateway;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class AssignCourierUseCaseTest {

    @Mock
    private CourierGateway courierGateway;

    @Mock
    private DeliveryGateway deliveryGateway;

    @InjectMocks
    private AssignCourierUseCaseImpl assignCourierUseCase;

    private UUID courierId;
    private UUID deliveryId;
    private Courier activeCourier;
    private Courier inactiveCourier;
    private Delivery delivery;

    @BeforeEach
    void setUp() {
        courierId = UUID.randomUUID();
        deliveryId = UUID.randomUUID();

        activeCourier = mock(Courier.class);
        inactiveCourier = mock(Courier.class);
        delivery = mock(Delivery.class);
    }


    @Test
    @DisplayName("Should assign an active courier to an existing delivery")
    void shouldAssignActiveCourierToExistingDelivery() {
        when(activeCourier.isActive()).thenReturn(true);
        when(courierGateway.findById(courierId)).thenReturn(Optional.of(activeCourier));
        when(deliveryGateway.findById(deliveryId)).thenReturn(Optional.of(delivery));
        when(deliveryGateway.save(delivery)).thenReturn(delivery);

        Delivery result = assignCourierUseCase.execute(courierId, deliveryId);

        assertNotNull(result);
        verify(delivery).assignCourier(activeCourier);
        verify(deliveryGateway).save(delivery);
    }

    @Test
    @DisplayName("Should throw IllegalStateException when courier is not found or is inactive")
    void shouldThrowExceptionWhenCourierNotFoundOrInactive() {
        when(inactiveCourier.isActive()).thenReturn(false);
        when(courierGateway.findById(courierId)).thenReturn(Optional.of(inactiveCourier));

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> assignCourierUseCase.execute(courierId, deliveryId)
        );
        assertEquals("Courier not found or not active.", thrown.getMessage());

        verifyNoInteractions(deliveryGateway);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when delivery is not found")
    void shouldThrowExceptionWhenDeliveryNotFound() {
        when(courierGateway.findById(courierId)).thenReturn(Optional.of(activeCourier));
        when(activeCourier.isActive()).thenReturn(true);

        when(deliveryGateway.findById(deliveryId)).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> assignCourierUseCase.execute(courierId, deliveryId)
        );

        assertEquals("Delivery not found", thrown.getMessage());
    }

}
