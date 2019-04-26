package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.Item;
import com.gmail.zagurskaya.service.model.ItemDTO;

public interface ItemConverter {

    ItemDTO toDTO(Item item);

    Item toEntity(ItemDTO item);

}
