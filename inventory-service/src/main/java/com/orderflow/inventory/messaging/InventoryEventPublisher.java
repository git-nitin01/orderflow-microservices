package com.orderflow.inventory.messaging;

import com.orderflow.common.events.OrderEvent;
import com.orderflow.common.kafka.Topics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventPublisher {
    private final KafkaTemplate<String, OrderEvent> kafka;

    public void publishValidated(OrderEvent event) {
        kafka.send(Topics.ORDER_VALIDATED, event.getOrderId(), event);
    }

}
