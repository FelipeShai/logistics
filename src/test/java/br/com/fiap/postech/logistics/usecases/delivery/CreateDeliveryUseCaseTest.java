package br.com.fiap.postech.logistics.usecases.delivery;

import br.com.fiap.postech.logistics.application.exception.custom.DuplicateOrderIdException;
import br.com.fiap.postech.logistics.application.usecases.delivery.CreateDeliveryUseCaseImpl;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateDeliveryUseCaseImplTest {

    @Mock
    private DeliveryGateway deliveryGateway;

    @InjectMocks
    private CreateDeliveryUseCaseImpl createDeliveryUseCase;

    private Delivery delivery;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        delivery = mock(Delivery.class);
        when(delivery.getOrderId()).thenReturn(orderId);
    }

    @Test
    @DisplayName("Should create a new delivery when the orderId does not exist in the system.")
    void shouldCreateNewDeliveryWhenOrderIdDoesNotExist() {
        // Arrange
        when(deliveryGateway.findByOrderId(orderId)).thenReturn(Optional.empty());
        when(deliveryGateway.save(delivery)).thenReturn(delivery);

        // Act
        Delivery result = createDeliveryUseCase.execute(delivery);

        // Assert
        assertNotNull(result);
        assertEquals(delivery, result);
        verify(deliveryGateway, times(1)).save(delivery);
    }

    @Test
    @DisplayName("Should update an existing delivery when the orderId already exists.")
    void shouldUpdateExistingDeliveryWhenOrderIdAlreadyExists() {
        Delivery existingDelivery = mock(Delivery.class);

        when(deliveryGateway.findByOrderId(orderId)).thenReturn(Optional.of(existingDelivery));

        when(deliveryGateway.save(existingDelivery)).thenReturn(existingDelivery);

        Delivery result = createDeliveryUseCase.execute(delivery);

        assertNotNull(result);
        assertEquals(existingDelivery, result);
        verify(existingDelivery, times(1)).updateFrom(delivery);
        verify(deliveryGateway, times(1)).save(existingDelivery);
        verify(deliveryGateway, never()).save(delivery);
    }


    @Test
    @DisplayName("Should throw DuplicateOrderIdException when trying to save a delivery with a duplicate orderId.")
    void shouldThrowDuplicateOrderIdExceptionWhenDataIntegrityViolationOccurs() {
        when(deliveryGateway.findByOrderId(orderId)).thenReturn(Optional.empty());
        when(deliveryGateway.save(delivery)).thenThrow(new DataIntegrityViolationException("Duplicate key"));

        DuplicateOrderIdException thrown = assertThrows(
                DuplicateOrderIdException.class,
                () -> createDeliveryUseCase.execute(delivery)
        );

        String expectedMessage = "Order ID already exists: " + orderId;
        assertEquals(expectedMessage, thrown.getMessage());
    }
}