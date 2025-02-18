package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryEntity;
import br.com.fiap.postech.logistics.infrastructure.persistence.mapper.DeliveryEntityMapper;
import br.com.fiap.postech.logistics.interfaces.gateway.database.DeliveryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindDeliveriesByZipUseCaseImpl implements FindDeliveriesByZipUseCase {

    private final DeliveryGateway deliveryGateway;
    private final DeliveryEntityMapper mapper;

    public FindDeliveriesByZipUseCaseImpl(DeliveryGateway deliveryGateway, DeliveryEntityMapper mapper) {
        this.deliveryGateway = deliveryGateway;
        this.mapper = mapper;
    }

    @Override
    public List<Delivery> execute(String zip) {
        List<DeliveryEntity> entities = deliveryGateway.findByAddressPostalCode(zip);

        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
