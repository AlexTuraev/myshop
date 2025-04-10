package org.tasks.myshop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tasks.myshop.dao.model.CartEntity;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByItemIdAndCartId(Long itemId, Long cartId);

    void deleteByItemIdAndCartId(Long itemId, Long cartId);

}
