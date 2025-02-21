package br.com.fiap.postech.logistics.factory;

import br.com.fiap.postech.logistics.application.factory.CourierFactoryImpl;
import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourierFactoryImplTest {

    private CourierFactoryImpl courierFactory;

    @BeforeEach
    void setUp() {
        courierFactory = new CourierFactoryImpl();
    }

    @Test
    void testCreate_withId() {
        UUID id = UUID.randomUUID();
        String name = "João da Silva";
        String phoneNumber = "11999999999";
        boolean active = true;

        // Chama o método de criação com ID
        Courier courier = courierFactory.create(id, name, phoneNumber, active);

        assertNotNull(courier);
        assertEquals(id, courier.getId());
        assertEquals(name, courier.getName());
        assertEquals(phoneNumber, courier.getPhoneNumber());
        assertTrue(courier.isActive());
    }

    @Test
    void testCreate_withoutId() {
        String name = "Maria Souza";
        String phoneNumber = "11988888888";
        boolean active = false;

        Courier courier = courierFactory.create(name, phoneNumber, active);

        assertNotNull(courier);
        assertNull(courier.getId());
        assertEquals(name, courier.getName());
        assertEquals(phoneNumber, courier.getPhoneNumber());
        assertFalse(courier.isActive());
    }

    @Test
    void testCreate_fromDTO() {
        CourierRequestDTO dto = new CourierRequestDTO(UUID.randomUUID(),"Pedro Santos", "11977777777", true);

        Courier courier = courierFactory.createFromDTO(dto);

        assertNotNull(courier);
        assertNull(courier.getId(), "O ID deve ser null ao criar apenas com name/phone/active");
        assertEquals(dto.name(), courier.getName());
        assertEquals(dto.phoneNumber(), courier.getPhoneNumber());
        assertTrue(courier.isActive());
    }
}
