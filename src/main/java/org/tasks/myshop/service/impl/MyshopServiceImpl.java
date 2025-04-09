package org.tasks.myshop.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.tasks.myshop.dao.model.ItemEntity;
import org.tasks.myshop.dao.model.ItemPicsEntity;
import org.tasks.myshop.dao.repository.ItemRespository;
import org.tasks.myshop.dto.ItemDto;
import org.tasks.myshop.service.MyshopService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyshopServiceImpl implements MyshopService {

    private final int DEFAULT_PAGE_SIZE = 3;
    private final int DEFAULT_PAGE_NUMBER = 0;

    private final ItemRespository itemRespository;

    public MyshopServiceImpl(ItemRespository itemRespository) {
        this.itemRespository = itemRespository;
    }

    @Override
    public List<ItemDto> getItems(String search, Integer pageSize, Integer pageNumber) {
        return List.of();
    }

    @Override
    public Model getItemsModel(Model model, String search, Integer pageSize, Integer pageNumber) {
        int pageLimit = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
        int pageNo = pageNumber == null ? DEFAULT_PAGE_NUMBER : pageNumber - 1;

        List<ItemDto> items = getItems(search, pageLimit, pageNo);

        model.addAttribute("items", items);
//        model.addAttribute("paging", new PagingDto(pageLimit, pageNo+1, total));
        return model;
    }

    @Override
    public void loadItemsFromCsv(MultipartFile file, MultipartFile[] images) throws IOException, CsvException {
        List<String[]> records;

        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReader(reader);
            records = csvReader.readAll();
            if (!records.isEmpty()) {
                records.remove(0); // удаляем заголовок csv-файла
            }
        }catch (IOException e) {
            throw e;  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }

        List<ItemEntity> items = saveItems(records);


    }

    private List<ItemEntity> saveItems(List<String[]> records) {
        List<ItemEntity> items = new ArrayList<>();

        records.forEach(strings -> {
            ItemEntity entity = new ItemEntity();
            entity.setTitle(strings[0]);
            entity.setDescription(strings[1]);
            entity.setPrice(BigDecimal.valueOf(Double.parseDouble(strings[2])));
            entity.setQuantity(Integer.valueOf(strings[3]));
            items.add(entity);
        });

        return itemRespository.saveAll(items);
    }

  /*  private void saveImages(MultipartFile[] images, List<Long> itemIds) {
        Map<String, MultipartFile> imagesMap = new HashMap<>();

        for (MultipartFile image : images) {
            imagesMap.put(image.getOriginalFilename(), image);
        }

        List<ItemPicsEntity> itemPics = new ArrayList<>();
        MultipartFile imageFile = imagesMap.get(strings[4]);
        if (imageFile != null) {
            ItemPicsEntity itemPicsEntity = new ItemPicsEntity();
            itemPicsEntity.setImageType(imageFile.getContentType());
            itemPicsEntity.setImage(imageFile.getBytes());
        }
    }*/

}
