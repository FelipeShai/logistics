package br.com.fiap.postech.logistics.application.factory;

import br.com.fiap.postech.logistics.domain.factory.CourierFactory;
import br.com.fiap.postech.logistics.domain.model.Courier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CourierFactoryImpl implements CourierFactory {

    public Courier create(UUID id, String name, String phoneNumber, boolean active) {
        return new Courier(id, name, phoneNumber, active);
    }

    public Courier create(String name, String phoneNumber, boolean active) {
        return new Courier(name, phoneNumber, active);
    }
}
