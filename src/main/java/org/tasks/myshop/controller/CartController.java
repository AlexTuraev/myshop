package org.tasks.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tasks.myshop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public String getCartByCartId(@PathVariable("id") Long cartId, Model model) {
        model = cartService.getCartByCartId(model, cartId);
        return "cart";
    }

    @PostMapping("/{id}/buy")
    public String cartBuy(@PathVariable("id") Long cartId, Model model) {
        cartService.purchase(model, cartId);
        return "cart";
    }
}
