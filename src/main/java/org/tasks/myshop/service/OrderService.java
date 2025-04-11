package org.tasks.myshop.service;

import org.tasks.myshop.dao.model.OrderEntity;

import java.util.List;

public interface OrderService {

    Long getNextOrderId();

    List<OrderEntity> saveAll(List<OrderEntity> orders);
}
