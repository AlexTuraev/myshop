package org.tasks.myshop.service;

import org.tasks.myshop.dao.model.CartEntity;

import java.util.Optional;

public interface CartService {

    Optional<CartEntity> getCartByItemId(Long itemId, Long cartId);

}
