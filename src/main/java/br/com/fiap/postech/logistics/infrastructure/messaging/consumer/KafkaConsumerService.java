package br.com.fiap.postech.logistics.infrastructure.messaging.consumer;

import br.com.fiap.postech.logistics.domain.events.OrderCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
public class KafkaConsumerService {

    @Bean
    public Consumer<OrderCreatedEvent> orderCreatedProcessor() {
        return event -> {
            System.out.println("📥 Received OrderCreatedEvent: " + event);
            // Aqui pode chamar outro serviço para processar o pedido
        };
    }
}
