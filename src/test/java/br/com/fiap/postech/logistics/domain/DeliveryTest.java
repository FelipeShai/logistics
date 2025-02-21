package br.com.fiap.postech.logistics.domain;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void shouldUpdateTracking() {
        Delivery delivery = new Delivery(null, null, null, null, null, null, null, null);
        delivery.updateTracking(1.23, 4.56);

        assertEquals(1.23, delivery.getLatitude());
        assertEquals(4.56, delivery.getLongitude());
    }
}
