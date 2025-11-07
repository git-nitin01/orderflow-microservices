package com.orderflow.inventory.messaging;

import com.orderflow.common.events.OrderEvent;
import com.orderflow.common.kafka.Topics;
import com.orderflow.common.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventConsumer {

    private final InventoryEventPublisher eventPublisher;

    @KafkaListener(topics = Topics.ORDER_CREATED, groupId = "${spring.kafka.consumer.group-id")
    public void onOrderCreated(OrderEvent in) {
        boolean inStock = in.getQuantity() <= 10;

        OrderEvent out = OrderEvent.builder()
                .orderId(in.getOrderId())
                .sku(in.getSku())
                .quantity(in.getQuantity())
                .amountCents(in.getAmountCents())
                .status(inStock ? OrderStatus.VALIDATED : OrderStatus.FAILED)
                .reason(inStock ? null : "Insufficient stock")
                .source("inventory-service")
                .build();

        eventPublisher.publishValidated(out);
        System.out.printf("[inventory] %s -> %s%n", in.getOrderId(), in.getStatus());
    }
}
