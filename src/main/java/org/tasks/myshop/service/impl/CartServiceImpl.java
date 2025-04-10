package org.tasks.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.tasks.myshop.dao.model.CartEntity;
import org.tasks.myshop.dao.repository.CartRepository;
import org.tasks.myshop.service.CartService;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final Long CART_ID = 1L;
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<CartEntity> getCartByItemId(Long itemId, Long cartId) {
        return cartRepository.findByItemId(itemId);
    }

}
