package com.orderflow.order.mapper;

import com.orderflow.common.dto.OrderDTO;
import com.orderflow.order.domain.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDto(Order entity);

    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(OrderDTO dto, @MappingTarget Order entity);
}
