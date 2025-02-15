package br.com.fiap.postech.logistics.application.usecases.courier;

import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.interfaces.gateway.database.CourierGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateCourierUseCaseImpl implements CreateCourierUseCase {

    private final CourierGateway courierGateway;

    public CreateCourierUseCaseImpl(CourierGateway courierGateway) {
        this.courierGateway = courierGateway;
    }

    @Override
    public Courier execute(Courier courier) {
        return courierGateway.save(courier);
    }
}
