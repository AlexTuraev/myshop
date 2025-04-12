package org.tasks.myshop.service.impl;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tasks.myshop.dao.model.CartEntity;
import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.dao.model.complexid.OrderEntityId;
import org.tasks.myshop.dao.repository.OrderRepository;
import org.tasks.myshop.dto.InnerOrder;
import org.tasks.myshop.dto.OrderDto;
import org.tasks.myshop.service.OrderService;
import org.tasks.myshop.service.mapper.OrderMapper;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<Long, InnerOrder> findAll() {
        List<OrderDto> dtos = orderRepository.findAll()
                .stream().map(orderMapper::toDto).toList();
        Map<Long, List<OrderDto>> mapOrders = dtos.stream().collect(Collectors.groupingBy(OrderDto::getOrderId));

        Map<Long, InnerOrder> newMap = mapOrders.entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), new InnerOrder(e.getValue(), getTotalSum(e.getValue()))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return newMap;
    }

    @Override
    public List<OrderDto> getOrdersById(Long orderId) {
        return orderRepository.findAllWhereId(orderId)
                .stream().map(orderMapper::toDto).toList();
    }

    @Override
    public Model getModelOrdersById(Model model, Long orderId) {
        List<OrderDto> orderDtos = getOrdersById(orderId);

        model.addAttribute("afterPurchase", false);
        model.addAttribute("orderId", orderId);
        model.addAttribute("orders", orderDtos);
        model.addAttribute("totalSum", getTotalSum(orderDtos));

        return model;
    }

    public BigDecimal getTotalSum(List<OrderDto> orders) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderDto orderDto : orders) {
            BigDecimal sumItem = orderDto.getItem().getPrice().multiply(BigDecimal.valueOf(orderDto.getCountItem()));
            total = total.add(sumItem);
        }
        return total;
    }

}
