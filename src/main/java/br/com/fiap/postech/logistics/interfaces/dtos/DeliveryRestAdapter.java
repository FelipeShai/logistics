package br.com.fiap.postech.logistics.interfaces.dtos;

import br.com.fiap.postech.logistics.application.usecases.delivery.CreateDeliveryUseCase;
import br.com.fiap.postech.logistics.application.usecases.delivery.DeleteDeliveryUseCase;
import br.com.fiap.postech.logistics.application.usecases.delivery.GetDeliveryByIdUseCase;
import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.factory.DeliveryFactory;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class DeliveryRestAdapter {

    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final GetDeliveryByIdUseCase getDeliveryByIdUseCase;
    private final DeleteDeliveryUseCase deleteDeliveryUseCase;
    private final DeliveryFactory deliveryFactory;
    private final DeliveryAddressFactory deliveryAddressFactory;

    public DeliveryRestAdapter(CreateDeliveryUseCase createDeliveryUseCase,
                               GetDeliveryByIdUseCase getDeliveryByIdUseCase,
                               DeleteDeliveryUseCase deleteDeliveryUseCase,
                               DeliveryFactory deliveryFactory,
                               DeliveryAddressFactory deliveryAddressFactory) {
        this.createDeliveryUseCase = createDeliveryUseCase;
        this.getDeliveryByIdUseCase = getDeliveryByIdUseCase;
        this.deleteDeliveryUseCase = deleteDeliveryUseCase;
        this.deliveryFactory = deliveryFactory;
        this.deliveryAddressFactory = deliveryAddressFactory;
    }

    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO requestDTO) {

        Delivery delivery = deliveryFactory.create(
               UUID.randomUUID(),
               requestDTO.orderId(),
               requestDTO.customerId(),
               requestDTO.courierId(),
               requestDTO.status(),
               requestDTO.address(),
               LocalDateTime.now(),
               null
        );

        Delivery saved = createDeliveryUseCase.execute(delivery);
        return new DeliveryResponseDTO(saved.getId(), saved.getStatus(), saved.getAddress());
    }

    public Optional<DeliveryResponseDTO> getDeliveryById(UUID id) {
        return getDeliveryByIdUseCase.execute(id)
                .map(delivery -> new DeliveryResponseDTO(delivery.getId(), delivery.getStatus(), delivery.getAddress()));
    }

    public void deleteDelivery(UUID id) {
        deleteDeliveryUseCase.execute(id);
    }

    public record DeliveryRequestDTO(UUID orderId, UUID customerId, UUID courierId, DeliveryStatus status, DeliveryAddress address) {}

    public record DeliveryResponseDTO(UUID id, DeliveryStatus status, DeliveryAddress address) {}
}
