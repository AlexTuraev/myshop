package org.tasks.myshop.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.tasks.myshop.dto.ItemDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MyshopService {

    List<ItemDto> getItems(String search, Integer pageSize, Integer pageNumber);
    Model getItemsModel(Model model, String search, Integer pageSize, Integer pageNumber);
    void loadItemsFromCsv(MultipartFile file, MultipartFile[] images) throws IOException, CsvException;

}
