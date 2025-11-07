package com.orderflow.order.service;

import com.orderflow.common.dto.OrderDTO;
import com.orderflow.common.events.OrderEvent;
import com.orderflow.common.kafka.Topics;
import com.orderflow.common.model.OrderStatus;
import com.orderflow.order.domain.Order;
import com.orderflow.order.mapper.OrderMapper;
import com.orderflow.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final KafkaTemplate<String, OrderEvent> kafka;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO dto) {
        Order entity = mapper.toEntity(dto);
        entity.setStatus(OrderStatus.CREATED);
        Order order = repo.save(entity);


        OrderEvent event = OrderEvent.builder()
                .orderId(order.getId())
                .sku(order.getSku())
                .quantity(order.getQuantity())
                .amountCents(order.getAmountCents())
                .status(OrderStatus.CREATED)
                .source("order-service")
                .build();

        kafka.send(Topics.ORDER_CREATED, order.getId(), event);
        return mapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDTO> getById(String id) {
        return repo.findById(id).map(mapper::toDto);
    }
}
