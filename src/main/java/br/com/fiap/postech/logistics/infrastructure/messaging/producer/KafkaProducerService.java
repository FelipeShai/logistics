package br.com.fiap.postech.logistics.infrastructure.messaging.producer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final StreamBridge streamBridge;

    public KafkaProducerService(StreamBridge streamBridge, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.streamBridge = streamBridge;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        LOGGER.info("Enviando evento para Kafka: {}", event);
        kafkaTemplate.send("orders.created", event);
    }
}