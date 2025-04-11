package org.tasks.myshop.service.impl;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.dao.model.complexid.OrderEntityId;
import org.tasks.myshop.dao.repository.OrderRepository;
import org.tasks.myshop.dto.OrderDto;
import org.tasks.myshop.service.OrderService;
import org.tasks.myshop.service.mapper.OrderMapper;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Long getNextOrderId() {
        return orderRepository.getNextOrderId();
    }

    @Override
    public List<OrderEntity> saveAll(List<OrderEntity> orders) {
        return orderRepository.saveAll(orders);
    }

    @Override
    public List<OrderDto> findAll() {
//        var list = orderRepository.findModelOrders();

        var list1 = orderRepository.findAll();

        List<OrderDto> dtos =  orderRepository.findAll()
                .stream().map(orderMapper::toDto).toList();

        return null;
    }

}
