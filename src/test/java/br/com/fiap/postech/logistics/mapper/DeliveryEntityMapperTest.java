package br.com.fiap.postech.logistics.mapper;

import br.com.fiap.postech.logistics.domain.factory.DeliveryFactory;
import br.com.fiap.postech.logistics.domain.model.*;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.CourierEntity;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryAddressEntity;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryEntity;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.CourierMapper;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.DeliveryAddressMapper;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.DeliveryEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryEntityMapperTest {

    private DeliveryFactory deliveryFactory;
    private DeliveryAddressMapper addressMapper;
    private CourierMapper courierMapper;
    private DeliveryEntityMapper mapper;

    @BeforeEach
    void setUp() {
        deliveryFactory = mock(DeliveryFactory.class);
        addressMapper = mock(DeliveryAddressMapper.class);
        courierMapper = mock(CourierMapper.class);
        mapper = new DeliveryEntityMapper(deliveryFactory, addressMapper, courierMapper);
    }

    @Test
    void shouldMapDomainToEntityAndBack() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        Courier courier = new Courier(UUID.randomUUID(), "John Doe", "1234567890", true);
        DeliveryStatus status = DeliveryStatus.IN_TRANSIT;
        DeliveryAddress address = new DeliveryAddress(UUID.randomUUID(), "123 Street", "City", "State", "12345", "Country", "Additional Info", "", "");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime deliveredAt = LocalDateTime.now().plusDays(1);
        Delivery domain = new Delivery(id, orderId, customerId, courier, status, address, createdAt, deliveredAt);
        domain.updateTracking(10.0, 20.0);

        when(courierMapper.toEntity(courier)).thenReturn(mock(CourierEntity.class));
        when(addressMapper.toEntity(address)).thenReturn(mock(DeliveryAddressEntity.class));

        DeliveryEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(10.0, entity.getLatitude());
        assertEquals(20.0, entity.getLongitude());

        when(courierMapper.toDomain(any())).thenReturn(courier);
        when(addressMapper.toDomain(any())).thenReturn(address);
        when(deliveryFactory.create(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(domain);

        Delivery resultDomain = mapper.toDomain(entity);
        assertNotNull(resultDomain);
        assertEquals(10.0, resultDomain.getLatitude());
        assertEquals(20.0, resultDomain.getLongitude());
    }
}