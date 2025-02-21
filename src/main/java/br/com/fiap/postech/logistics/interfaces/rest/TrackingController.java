package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.application.usecases.TrackingUseCase;
import br.com.fiap.postech.logistics.interfaces.dtos.TrackingRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RestController
@RequestMapping("/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingUseCase trackingUseCase;

    @PostMapping
    public ResponseEntity<Void> updateTracking(
            @RequestParam UUID deliveryId,
            @RequestBody TrackingRequestDTO request
    ) {
        trackingUseCase.updateLocation(deliveryId, request.latitude(), request.longitude());
        return ResponseEntity.ok().build();
    }
}