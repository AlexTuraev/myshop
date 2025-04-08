package org.tasks.myshop.service;

import org.springframework.ui.Model;
import org.tasks.myshop.dto.ItemDto;

import java.util.List;

public interface MyshopService {

    List<ItemDto> getItems(String search, Integer pageSize, Integer pageNumber);
    Model getItemsModel(Model model, String search, Integer pageSize, Integer pageNumber);

}
