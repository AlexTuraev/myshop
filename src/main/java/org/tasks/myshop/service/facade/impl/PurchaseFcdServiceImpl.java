package org.tasks.myshop.service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.tasks.myshop.dao.model.CartEntity;
import org.tasks.myshop.dao.model.OrderEntity;
import org.tasks.myshop.service.CartService;
import org.tasks.myshop.service.OrderService;
import org.tasks.myshop.service.facade.PurchaseFcdService;
import org.tasks.myshop.service.mapper.CartOrderMapper;

import java.util.List;

@Service
public class PurchaseFcdServiceImpl implements PurchaseFcdService {

    private final CartService cartService;
    private final OrderService orderService;
    private final CartOrderMapper cartOrderMapper;

    public PurchaseFcdServiceImpl(CartService cartService, OrderService orderService, CartOrderMapper cartOrderMapper) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.cartOrderMapper = cartOrderMapper;
    }

    @Override
    @Transactional
    public Model purchase(Model model, Long cartId) {
        List<CartEntity> carts = cartService.getCartsByCartId(cartId);

        Long nextOrderId = orderService.getNextOrderId();
        List<OrderEntity> orders = carts.stream()
                .map(cartOrderMapper::CartToOrderEntity)
                .map(orderEntity -> orderEntity.orderId(nextOrderId))
                .toList();
        List<OrderEntity> list = orderService.saveAll(orders);

        cartService.deleteAll(carts);

        return null;
    }



}
