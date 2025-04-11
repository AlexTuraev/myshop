package org.tasks.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tasks.myshop.service.CartService;
import org.tasks.myshop.service.facade.PurchaseFcdService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final PurchaseFcdService purchaseFcdService;

    public CartController(CartService cartService, PurchaseFcdService purchaseFcdService) {
        this.cartService = cartService;
        this.purchaseFcdService = purchaseFcdService;
    }

    @GetMapping("/{id}")
    public String getCartByCartId(@PathVariable("id") Long cartId, Model model) {
        model = cartService.getModelByCartId(model, cartId);
        return "cart";
    }

    @PostMapping("/{id}/buy")
    public String cartBuy(@PathVariable("id") Long cartId, Model model) {
        purchaseFcdService.purchase(model, cartId);
        return "order";
    }
}
