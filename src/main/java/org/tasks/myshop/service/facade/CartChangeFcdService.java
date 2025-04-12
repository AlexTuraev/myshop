package org.tasks.myshop.service.facade;

import org.springframework.ui.Model;

public interface CartChangeFcdService {

    int getDelta(Long cartId, Long itemId, String action);

    Model updateItemInCart(Long cartId, Long itemId, String action);

}
