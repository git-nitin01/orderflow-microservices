package com.orderflow.common.events;

import com.orderflow.common.model.OrderStatus;
import lombok.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderEvent {
    private String orderId;
    private String sku;
    private int quantity;
    private long amountCents;
    private OrderStatus status;
    private String source;
    @Builder.Default
    private Instant timestamp = Instant.now();
    private String reason;
}
