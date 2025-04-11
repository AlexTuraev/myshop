package org.tasks.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tasks.myshop.dao.model.CartEntity;
import org.tasks.myshop.dao.repository.CartRepository;
import org.tasks.myshop.service.CartService;
import org.tasks.myshop.service.mapper.CartMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public Optional<CartEntity> getCartByItemIdAndCartId(Long itemId, Long cartId) {
        return cartRepository.findByItemIdAndCartId(itemId, cartId);
    }

    @Override
    public CartEntity updateCountItem(Long itemId, Long cartId, int deltaCount) {
        CartEntity cart = getCartByItemIdAndCartId(itemId, cartId)
                .orElse(new CartEntity(cartId, itemId, 0, null));

        if (cart.getCountItem() == 0 && deltaCount < 0) {
            throw new RuntimeException("Попытка уменьшить отсутствующее значение");
        }

        cart.setCountItem(cart.getCountItem() + deltaCount);
        if (cart.getCountItem() > 0) {
            return cartRepository.save(cart);
        }
        else {
            cartRepository.deleteByItemIdAndCartId(itemId, cartId);
            return cart;
        }
    }

    @Override
    public Model getCartByCartId(Model model, Long cartId) {
        List<CartEntity> cart = cartRepository.getCartModelByCartId(cartId);
        model.addAttribute("cartItems", cart.stream().map(cartMapper::toDto).toList());
        model.addAttribute("totalSum", getTotalSum(cart));
        return model;
    }

    private BigDecimal getTotalSum(List<CartEntity> carts) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartEntity cart : carts) {
            BigDecimal sumItem = cart.getItem().getPrice().multiply(BigDecimal.valueOf(cart.getCountItem()));
            total = total.add(sumItem);
        }
        return total;
    }

}
