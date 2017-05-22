package com.example.service.impl;

import com.example.dto.ItemDto;
import com.example.model.Item;
import com.example.repository.ItemRepository;
import com.example.service.ItemService;
import com.example.transformers.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private Transformers transformers;

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = (List<Item>)itemRepository.findAll();
        List<ItemDto> itemDtoList = new ArrayList<>();
        items.stream().forEach(item -> itemDtoList.add((ItemDto)transformers.convertEntityToDto(item, ItemDto.class)));
        return itemDtoList;
    }
}
