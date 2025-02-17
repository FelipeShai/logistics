package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.application.usecases.delivery.CreateDeliveryUseCase;
import br.com.fiap.postech.logistics.application.usecases.delivery.GetDeliveryByIdUseCaseImpl;
import br.com.fiap.postech.logistics.interfaces.adapters.DeliveryRestAdapter;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryRequestDTO;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRestAdapter deliveryRestAdapter;
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final GetDeliveryByIdUseCaseImpl getDeliveryByIdUseCase;

    public DeliveryController(DeliveryRestAdapter deliveryRestAdapter, CreateDeliveryUseCase createDeliveryUseCase, GetDeliveryByIdUseCaseImpl getDeliveryByIdUseCase) {
        this.deliveryRestAdapter = deliveryRestAdapter;
        this.createDeliveryUseCase = createDeliveryUseCase;
        this.getDeliveryByIdUseCase = getDeliveryByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<DeliveryResponseDTO> create(@Valid @RequestBody DeliveryRequestDTO requestDTO) {

        var delivery = deliveryRestAdapter.toDomain(requestDTO);
        var saved = createDeliveryUseCase.execute(delivery);
        var response = deliveryRestAdapter.toResponse(saved);

        return ResponseEntity.created(URI.create("/deliveries/" + response.id())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> findById(@PathVariable UUID id) {
        var delivery = getDeliveryByIdUseCase.execute(id);
        return ResponseEntity.ok(deliveryRestAdapter.toResponse(delivery));
    }
}
