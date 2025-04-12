package org.tasks.myshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tasks.myshop.dao.model.CartEntity;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private ItemPicsDto itemPics;

    private String base64Image;

//    private List<CartDto> carts;

}
