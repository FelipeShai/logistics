package br.com.fiap.postech.logistics.usecases.courier;

import br.com.fiap.postech.logistics.application.usecases.courier.CreateCourierUseCaseImpl;
import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.interfaces.gateway.database.CourierGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCourierUseCaseTest {

    @Mock
    private CourierGateway courierGateway;

    @InjectMocks
    private CreateCourierUseCaseImpl createCourierUseCase;

    private Courier courier;

    @BeforeEach
    void setUp() {
        courier = new Courier(
                UUID.randomUUID(),
                "John Doe",
                "123456789",
                true
        );
    }

    @Test
    @DisplayName("Should create a new courier")
    void shouldCreateNewCourier() {
        when(courierGateway.save(courier)).thenReturn(courier);

        Courier result = createCourierUseCase.execute(courier);

        assertNotNull(result);
        assertEquals(courier, result);

        verify(courierGateway, times(1)).save(courier);
    }
}
