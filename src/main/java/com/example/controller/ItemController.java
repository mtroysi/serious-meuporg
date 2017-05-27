package com.example.controller;

import com.example.dto.ItemDto;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Retourne la liste de tous les items.
     * @return liste de tous les items.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }
}
