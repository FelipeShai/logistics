package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.application.usecases.UpdateDeliveryStatusUseCase;
import br.com.fiap.postech.logistics.application.usecases.delivery.CreateDeliveryUseCase;
import br.com.fiap.postech.logistics.application.usecases.delivery.FindDeliveriesByZipUseCaseImpl;
import br.com.fiap.postech.logistics.application.usecases.delivery.GetDeliveryByIdUseCaseImpl;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;
import br.com.fiap.postech.logistics.interfaces.adapters.DeliveryRestAdapter;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryRequestDTO;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRestAdapter deliveryRestAdapter;
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final GetDeliveryByIdUseCaseImpl getDeliveryByIdUseCase;
    private final FindDeliveriesByZipUseCaseImpl findDeliveriesByZipUseCase;
    private final UpdateDeliveryStatusUseCase updateDeliveryStatusUseCase;

    public DeliveryController(
            DeliveryRestAdapter deliveryRestAdapter,
            CreateDeliveryUseCase createDeliveryUseCase,
            GetDeliveryByIdUseCaseImpl getDeliveryByIdUseCase,
            FindDeliveriesByZipUseCaseImpl findDeliveriesByZipUseCase,
            UpdateDeliveryStatusUseCase updateDeliveryStatusUseCase
    ) {
        this.deliveryRestAdapter = deliveryRestAdapter;
        this.createDeliveryUseCase = createDeliveryUseCase;
        this.getDeliveryByIdUseCase = getDeliveryByIdUseCase;
        this.findDeliveriesByZipUseCase = findDeliveriesByZipUseCase;
        this.updateDeliveryStatusUseCase = updateDeliveryStatusUseCase;
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

    @GetMapping
    public ResponseEntity<List<DeliveryResponseDTO>> findPendingDeliveriesByZip(
            @RequestParam(name = "zip") String zip) {

        List<Delivery> deliveries = findDeliveriesByZipUseCase.execute(zip);

        List<DeliveryResponseDTO> response = deliveries.stream()
                .map(deliveryRestAdapter::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestBody Map<String, String> request) {
        DeliveryStatus newStatus = DeliveryStatus.valueOf(request.get("status"));
        updateDeliveryStatusUseCase.execute(id, newStatus);
        return ResponseEntity.noContent().build();
    }
}
