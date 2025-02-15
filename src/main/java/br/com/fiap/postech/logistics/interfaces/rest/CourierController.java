package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.application.usecases.courier.CreateCourierUseCase;
import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.interfaces.adapters.CourierRestAdapter;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierRequestDTO;
import br.com.fiap.postech.logistics.interfaces.dtos.CourierResponseDTO;
import br.com.fiap.postech.logistics.interfaces.gateway.database.CourierGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    private final CourierRestAdapter courierRestAdapter;
    private final CreateCourierUseCase createCourierUseCase;
    private final CourierGateway courierGateway;

    public CourierController(
            CourierRestAdapter courierRestAdapter,
            CreateCourierUseCase createCourierUseCase,
            CourierGateway courierGateway
    ) {
        this.courierRestAdapter = courierRestAdapter;
        this.createCourierUseCase = createCourierUseCase;
        this.courierGateway = courierGateway;
    }

    @PostMapping
    public ResponseEntity<CourierResponseDTO> create(@RequestBody CourierRequestDTO requestDTO) {
        Courier courier = courierRestAdapter.toDomain(requestDTO);

        Courier saved = createCourierUseCase.execute(courier);

        CourierResponseDTO responseDTO = courierRestAdapter.toResponse(saved);

        return ResponseEntity.created(URI.create("/couriers/" + responseDTO.id()))
                .body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourierResponseDTO> findById(@PathVariable UUID id) {
        return courierGateway.findById(id)
                .map(courierRestAdapter::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CourierResponseDTO>> findAll() {
        List<Courier> couriers = courierGateway.findAll();
        List<CourierResponseDTO> responseList = couriers.stream()
                .map(courierRestAdapter::toResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        courierGateway.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
