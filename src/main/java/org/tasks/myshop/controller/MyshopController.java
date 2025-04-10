package org.tasks.myshop.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.tasks.myshop.dto.ItemDto;
import org.tasks.myshop.exception.LoadItemException;
import org.tasks.myshop.exception.SortException;
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
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber
    ) throws SortException {
        model = myshopService.getItemsModel(model, search, pageSize, pageNumber, sort);
        return "main";
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

    @GetMapping("/item/{id}")
    public String getItem(Model model, @PathVariable("id") Long id) {
        ItemDto itemDto = myshopService.getItemById(id);
        model.addAttribute("item", itemDto);
        return "item";
    }

    @PostMapping("/item/{id}/cart")
    public String changeItemCart(
            Model model,
            @PathVariable("id") Long id,
            @RequestParam("action") @Min(-1) @Max(1) int delta) {
        model = myshopService.changeItemCart(model, id, 1L, delta);
        return "item";
    }

    @PostMapping("/item/{id}/maincart")
    public String changeItemCartFromMain(
            Model model,
            @PathVariable("id") Long id,
            @RequestParam("action") @Min(-1) @Max(1) int delta) {
        model = myshopService.changeItemCart(model, id, 1L, delta);
        return "redirect:/";
    }


    @ExceptionHandler(LoadItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIOException(Model model, IOException ex) {
        model.addAttribute("reason", ex.getMessage());
        return "load-error";
    }

    @ExceptionHandler(SortException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSortException(Model model, SortException ex) {
        model.addAttribute("reason", ex.getMessage());
        return "load-error";
    }

}
