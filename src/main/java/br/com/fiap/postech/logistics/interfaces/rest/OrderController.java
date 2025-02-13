package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import br.com.fiap.postech.logistics.infrastructure.messaging.producer.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderCreatedEvent order) {
        kafkaProducerService.sendOrderCreatedEvent(order);
        return ResponseEntity.ok("Order event sent!");
    }
}
