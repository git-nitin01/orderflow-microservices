package com.orderflow.order.service;

import com.orderflow.common.dto.OrderDTO;
import com.orderflow.common.model.OrderStatus;
import com.orderflow.order.domain.Order;
import com.orderflow.order.mapper.OrderMapper;
import com.orderflow.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;
    private final OrderMapper mapper;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO dto) {
        Order entity = mapper.toEntity(dto);
        entity.setStatus(OrderStatus.CREATED);
        Order saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDTO> getById(String id) {
        return repo.findById(id).map(mapper::toDto);
    }
}
