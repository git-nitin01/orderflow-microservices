package com.orderflow.order.service;

import com.orderflow.common.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {
    OrderDTO create(OrderDTO dto);

    Optional<OrderDTO> getById(String id);
}
