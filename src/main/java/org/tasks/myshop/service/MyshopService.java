package org.tasks.myshop.service;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.tasks.myshop.dao.model.ItemEntity;
import org.tasks.myshop.dto.ItemDto;
import org.tasks.myshop.enums.SortEnum;
import org.tasks.myshop.exception.LoadItemException;
import org.tasks.myshop.exception.SortException;

import java.util.List;

public interface MyshopService {

    Page<ItemEntity> getItems(String search, Integer pageSize, Integer pageNumber, SortEnum sortType);
    Model getItemsModel(Model model, String search, Integer pageSize, Integer pageNumber, String sort) throws SortException;
    void loadItemsFromCsv(MultipartFile file, MultipartFile[] images) throws LoadItemException;

    ItemDto getItemById(Long id);

    Model changeItemCart(Model model, Long id, int delta);
}
