package org.tasks.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tasks.myshop.dto.ItemDto;
import org.tasks.myshop.service.MyshopService;

import java.util.List;

@Service
public class MyshopServiceImpl implements MyshopService {

    private final int DEFAULT_PAGE_SIZE = 3;
    private final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public List<ItemDto> getItems(String search, Integer pageSize, Integer pageNumber) {
        return List.of();
    }

    @Override
    public Model getItemsModel(Model model, String search, Integer pageSize, Integer pageNumber) {
        int pageLimit = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
        int pageNo = pageNumber == null ? DEFAULT_PAGE_NUMBER : pageNumber-1;

        List<ItemDto> items =  getItems(search, pageLimit, pageNo);

        model.addAttribute("items", items);
//        model.addAttribute("paging", new PagingDto(pageLimit, pageNo+1, total));
        return model;
    }

}
