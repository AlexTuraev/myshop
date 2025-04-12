package org.tasks.myshop.service;

import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.dto.InnerOrder;
import org.tasks.myshop.dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Long getNextOrderId();

    List<OrderEntity> saveAll(List<OrderEntity> orders);
    Map<Long, InnerOrder> findAll();
}
