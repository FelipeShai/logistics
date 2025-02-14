package br.com.fiap.postech.logistics.infrastructure.messaging.consumer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import br.com.fiap.postech.logistics.infrastructure.observability.KafkaLoggingInterceptor;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryRestAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Slf4j
@Service
public class KafkaConsumerService {

    private final DeliveryRestAdapter deliveryRestAdapter;

    public KafkaConsumerService(DeliveryRestAdapter deliveryRestAdapter) {
        this.deliveryRestAdapter = deliveryRestAdapter;
    }

    @Bean
    public Consumer<OrderCreatedEvent> orderCreatedProcessor() {
        return event -> {
            log.info("[Kafka Consumer] Class={}, Method={}, Message received: {}", "KafkaConsumerService", "orderCreatedProcessor", event);

            deliveryRestAdapter.createDelivery(event);

            System.out.println("📥 Received OrderCreatedEvent: " + event);
            // Aqui pode chamar outro serviço para processar o pedido
        };
    }
}
