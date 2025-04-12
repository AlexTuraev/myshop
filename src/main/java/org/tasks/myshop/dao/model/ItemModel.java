package org.tasks.myshop.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class ItemModel {

    private ItemEntity item;
    private Integer count;

    public ItemModel(ItemEntity item, Integer count) {
        this.item = item;
        this.count = count;
    }
}
