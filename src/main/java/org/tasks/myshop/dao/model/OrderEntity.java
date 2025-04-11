package org.tasks.myshop.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tasks.myshop.dao.model.complexid.OrderEntityId;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "item_id"}))
@IdClass(OrderEntityId.class)
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "count_item")
    private Integer countItem;

    @Column(name = "price")
    private BigDecimal price;

}
