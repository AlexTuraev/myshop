package org.tasks.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tasks.myshop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @GetMapping("/{id}")
    public String getCartByCartId(@PathVariable("id") Long cartId, Model model) {
        return null;
//        return "cart";
    }
}
