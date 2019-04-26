package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.Item;
import com.gmail.zagurskaya.service.converter.ItemConverter;
import com.gmail.zagurskaya.service.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverterImpl implements ItemConverter {

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setStatus(item.getStatus());
        return itemDTO;
    }

    @Override
    public Item toEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setStatus(itemDTO.getStatus());
        return item;
    }


}
