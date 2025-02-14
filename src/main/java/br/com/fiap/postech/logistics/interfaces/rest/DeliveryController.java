package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.application.usecases.delivery.CreateDeliveryUseCase;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryRestAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRestAdapter deliveryRestAdapter;
    private final CreateDeliveryUseCase createDeliveryUseCase;

    public DeliveryController(DeliveryRestAdapter deliveryRestAdapter, CreateDeliveryUseCase createDeliveryUseCase) {
        this.deliveryRestAdapter = deliveryRestAdapter;
        this.createDeliveryUseCase = createDeliveryUseCase;
    }

    @PostMapping
    public ResponseEntity<DeliveryRestAdapter.DeliveryResponseDTO> create(@RequestBody DeliveryRestAdapter.DeliveryRequestDTO requestDTO) {

        var delivery = deliveryRestAdapter.toDomain(requestDTO);
        var saved = createDeliveryUseCase.execute(delivery);
        var response = deliveryRestAdapter.toResponse(saved);

        return ResponseEntity.created(URI.create("/deliveries/" + response.id())).body(response);
    }
}
