package org.tasks.myshop.service;

import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.dto.OrderDto;

import java.util.List;

public interface OrderService {

    Long getNextOrderId();

    List<OrderEntity> saveAll(List<OrderEntity> orders);
    List<OrderDto> findAll();
}
