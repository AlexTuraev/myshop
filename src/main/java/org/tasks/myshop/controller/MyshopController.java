package org.tasks.myshop.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.tasks.myshop.exception.LoadItemException;
import org.tasks.myshop.service.MyshopService;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MyshopController {

    private final MyshopService myshopService;

    public MyshopController(MyshopService myshopService) {
        this.myshopService = myshopService;
    }

    @GetMapping
    public String getItems(
            Model model,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber
    ) {
        myshopService.getItems(search, pageSize, pageNumber);
        return "";
    }

    @GetMapping("/upload")
    public String getUploadPage() {
        return "loaditems";
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String loadItemsFromCsv(
            @RequestPart("uploadcsvfile") MultipartFile file,
            @RequestPart("images") MultipartFile[] images) throws LoadItemException {
        myshopService.loadItemsFromCsv(file, images);
        return "load-success";
    }

    @ExceptionHandler(LoadItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIOException(IOException ex) {
        return "load-error";
    }

}
