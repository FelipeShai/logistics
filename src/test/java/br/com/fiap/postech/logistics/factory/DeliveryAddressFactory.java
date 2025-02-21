package br.com.fiap.postech.logistics.factory;

import br.com.fiap.postech.logistics.application.factory.DeliveryAddressFactoryImpl;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryAddressFactoryImplTest {

    private DeliveryAddressFactoryImpl addressFactory;

    @BeforeEach
    void setUp() {
        addressFactory = new DeliveryAddressFactoryImpl();
    }

    @Test
    void testCreate_withAllParams() {
        UUID id = UUID.randomUUID();
        String street = "Rua ABC";
        String number = "123";
        String complement = "Apto 10";
        String district = "Bairro XYZ";
        String city = "Cidade 123";
        String state = "SP";
        String country = "Brasil";
        String postalCode = "00000-000";

        DeliveryAddress address = addressFactory.create(
                id, street, number, complement, district, city, state, country, postalCode
        );

        assertNotNull(address);
        assertEquals(id, address.getId());
        assertEquals(street, address.getStreet());
        assertEquals(number, address.getNumber());
        assertEquals(complement, address.getComplement());
        assertEquals(district, address.getDistrict());
        assertEquals(city, address.getCity());
        assertEquals(state, address.getState());
        assertEquals(country, address.getCountry());
        assertEquals(postalCode, address.getPostalCode());
    }
}
