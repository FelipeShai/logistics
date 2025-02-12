package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryRestAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRestAdapter deliveryRestAdapter;

    public DeliveryController(DeliveryRestAdapter deliveryRestAdapter) {
        this.deliveryRestAdapter = deliveryRestAdapter;
    }

    @PostMapping
    public ResponseEntity<DeliveryRestAdapter.DeliveryResponseDTO> create(@RequestBody DeliveryRestAdapter.DeliveryRequestDTO requestDTO) {
        DeliveryRestAdapter.DeliveryResponseDTO responseDTO = deliveryRestAdapter.createDelivery(requestDTO);
        return ResponseEntity.created(URI.create("/deliveries/" + responseDTO.id())).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRestAdapter.DeliveryResponseDTO> getById(@PathVariable UUID id) {
        return deliveryRestAdapter.getDeliveryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deliveryRestAdapter.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }
}
