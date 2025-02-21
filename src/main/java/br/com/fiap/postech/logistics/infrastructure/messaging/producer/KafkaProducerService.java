package br.com.fiap.postech.logistics.infrastructure.messaging.producer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import br.com.fiap.postech.logistics.interfaces.dtos.OrderDeliveredDTO;
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
        streamBridge.send("orders.created", event);
        log.info("✅ OrderCreatedEvent enviado para Kafka: {}", event);
    }

    public void sendOrderDeliveredEvent(OrderDeliveredDTO event) {
        streamBridge.send("orders.delivered", event);
        log.info("✅ OrderDeliveredEvent enviado para Kafka: {}", event);
    }
}