package br.com.fiap.postech.logistics.infrastructure.messaging.consumer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;

import java.util.function.Consumer;

public interface KafkaConsumerService {
    Consumer<OrderCreatedEvent> orderCreatedProcessor();
}
