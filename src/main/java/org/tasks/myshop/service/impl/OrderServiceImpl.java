package org.tasks.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.dao.repository.OrderRepository;
import org.tasks.myshop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long getNextOrderId() {
        return orderRepository.getNextOrderId();
    }

    @Override
    public List<OrderEntity> saveAll(List<OrderEntity> orders) {
        return orderRepository.saveAll(orders);
    }

}
