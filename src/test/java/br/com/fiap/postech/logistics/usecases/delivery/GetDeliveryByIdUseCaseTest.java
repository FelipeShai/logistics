package br.com.fiap.postech.logistics.usecases.delivery;

import br.com.fiap.postech.logistics.application.usecases.delivery.GetDeliveryByIdUseCaseImpl;
import br.com.fiap.postech.logistics.domain.model.Delivery;
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
class GetDeliveryByIdUseCaseTest {

    @Mock
    private DeliveryGateway deliveryGateway;

    @InjectMocks
    private GetDeliveryByIdUseCaseImpl getDeliveryByIdUseCase;

    private UUID deliveryId;
    private Delivery delivery;

    @BeforeEach
    void setUp() {
        deliveryId = UUID.randomUUID();
        delivery = mock(Delivery.class);
    }

    @Test
    @DisplayName("Should return delivery when deliveryId exists")
    void shouldReturnDeliveryWhenIdExists() {
        when(deliveryGateway.findById(deliveryId)).thenReturn(Optional.of(delivery));

        Delivery result = getDeliveryByIdUseCase.execute(deliveryId);

        assertNotNull(result);
        assertEquals(delivery, result);

        verify(deliveryGateway, times(1)).findById(deliveryId);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when deliveryId does not exist")
    void shouldThrowExceptionWhenIdDoesNotExist() {
        when(deliveryGateway.findById(deliveryId)).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> getDeliveryByIdUseCase.execute(deliveryId)
        );

        assertEquals("Delivery not found", thrown.getMessage());
        verify(deliveryGateway, times(1)).findById(deliveryId);
    }
}
