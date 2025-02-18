package br.com.fiap.postech.logistics.infrastructure.messaging.producer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final StreamBridge streamBridge;

    public KafkaProducerService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        streamBridge.send("orders.created", event); // Envia evento via Spring Cloud Stream
        log.info("✅ OrderCreatedEvent enviado para Kafka: {}", event);
    }
}