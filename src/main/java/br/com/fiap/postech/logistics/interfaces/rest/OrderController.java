package br.com.fiap.postech.logistics.interfaces.rest;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import br.com.fiap.postech.logistics.infrastructure.messaging.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderCreatedEvent order) {

        log.info("[Kafka Producer] Class={}, Method={}, Sending order event to Kafka: {}",
                "OrderController", "createOrder", order);

        kafkaProducerService.sendOrderCreatedEvent(order);

        log.info("[Kafka Producer] Class={}, Method={}, Order event successfully sent: {}",
                "OrderController", "createOrder", order.orderId());

        return ResponseEntity.ok().build();
    }
}
