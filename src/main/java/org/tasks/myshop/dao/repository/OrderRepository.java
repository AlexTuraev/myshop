package org.tasks.myshop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tasks.myshop.dao.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT nextval('order_sequence')")
    Long getNextOrderId();

}
