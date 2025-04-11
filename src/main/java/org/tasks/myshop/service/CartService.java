package org.tasks.myshop.service;

import org.springframework.ui.Model;
import org.tasks.myshop.dao.model.CartEntity;

import java.util.Optional;

public interface CartService {

    Optional<CartEntity> getCartByItemIdAndCartId(Long itemId, Long cartId);

    CartEntity updateCountItem(Long itemId, Long cartId, int deltaCount);

    Model getCartByCartId(Model model, Long cartId);

    int getCountItemOrZeroIfAbsent(Long itemId, Long cartId);

    Model purchase(Model model, Long cartId);
}
