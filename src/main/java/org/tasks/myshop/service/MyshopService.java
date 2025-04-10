package org.tasks.myshop.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.tasks.myshop.dto.ItemDto;
import org.tasks.myshop.enums.SortEnum;
import org.tasks.myshop.exception.LoadItemException;
import org.tasks.myshop.exception.SortException;

import java.util.List;

public interface MyshopService {

    List<ItemDto> getItems(String search, Integer pageSize, Integer pageNumber, SortEnum sortType);
    Model getItemsModel(Model model, String search, Integer pageSize, Integer pageNumber, String sort) throws SortException;
    void loadItemsFromCsv(MultipartFile file, MultipartFile[] images) throws LoadItemException;

}
