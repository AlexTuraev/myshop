package org.tasks.myshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long orderId;

    private Long itemId;

    private Integer countItem;

    private BigDecimal price;

    private ItemDto item;

    private List<ItemDto> items;

}
