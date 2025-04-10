package org.tasks.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.tasks.myshop.dao.model.CartEntity;
import org.tasks.myshop.dao.repository.CartRepository;
import org.tasks.myshop.service.CartService;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<CartEntity> getCartByItemIdAndCartId(Long itemId, Long cartId) {
        return cartRepository.findByItemIdAndCartId(itemId, cartId);
    }

    @Override
    public CartEntity updateCountItem(Long itemId, Long cartId, int deltaCount) {
        CartEntity cart = getCartByItemIdAndCartId(itemId, cartId)
                .orElse(new CartEntity(cartId, itemId, 0, null));

        cart.setCountItem(cart.getCountItem() + deltaCount);
        if (cart.getCountItem() > 0) {
            return cartRepository.save(cart);
        }
        else {
            cartRepository.deleteByItemIdAndCartId(itemId, cartId);
            return null;
        }
    }

}
