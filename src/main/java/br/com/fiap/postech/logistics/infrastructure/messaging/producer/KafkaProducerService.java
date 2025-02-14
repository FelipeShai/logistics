package br.com.fiap.postech.logistics.infrastructure.messaging.producer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send("orders.created", event);
        log.info("Class={}, Method={}, Sending event to Kafka: {}", "KafkaProducerService", "sendOrderCreatedEvent", event);
    }
}