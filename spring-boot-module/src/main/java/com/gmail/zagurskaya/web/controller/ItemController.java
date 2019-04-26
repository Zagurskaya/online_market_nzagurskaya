package com.gmail.zagurskaya.web.controller;

import java.util.List;
import javax.validation.Valid;

import com.gmail.zagurskaya.service.ItemService;
import com.gmail.zagurskaya.service.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping({"/items", "/test"})
    public String getItems(Model model) {
        List<ItemDTO> items = itemService.getItems();
        model.addAttribute("items", items);
        return "items";
    }

    @PostMapping("/items/{id}")
    public String getItems(@PathVariable("id") Long id, @RequestParam String status) {
//        itemService.updateStatus(id, status);
        return "redirect:/items";
    }

    @GetMapping("/items/add")
    public String getItemView(ItemDTO itemDTO) {
//        itemService.add(itemDTO);
        int i =1;
        return "item";
    }

    @PostMapping("/items/add")
    public String addItem(@Valid @ModelAttribute ItemDTO itemDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "item";
        }
//        itemService.add(itemDTO);
        return "redirect:/items";
    }
}
