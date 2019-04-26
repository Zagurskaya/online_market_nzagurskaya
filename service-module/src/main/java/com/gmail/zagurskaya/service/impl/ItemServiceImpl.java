package com.gmail.zagurskaya.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.gmail.zagurskaya.repository.ItemRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.Item;
import com.gmail.zagurskaya.service.ItemService;
import com.gmail.zagurskaya.service.converter.ItemConverter;
import com.gmail.zagurskaya.service.model.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final ItemConverter itemConverter;
    private final ItemRepository itemRepository;
    private final ConnectionHandler connectionHandler;

    public ItemServiceImpl(ItemConverter itemConverter, ItemRepository itemRepository, ConnectionHandler connectionHandler) {
        this.itemConverter = itemConverter;
        this.itemRepository = itemRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<ItemDTO> getItems() {

        List<Item> items = itemRepository.getItems();
        List<ItemDTO> dtos = items.stream()
                .map(itemConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }
}
