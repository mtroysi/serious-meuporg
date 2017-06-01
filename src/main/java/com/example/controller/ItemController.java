package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ItemDTO;
import com.example.service.ItemService;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Retourne la liste de tous les items par utilisateur.
     * @return liste de tous les items.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<ItemDTO> getAllItems(@PathVariable("id") Long idUser) {
        return itemService.getAllItemsByUser(idUser);
    }
    
    /**
     * Création d'un nouveau item
     * @param item
     * @return itemDTO 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ItemDTO createItem(@RequestBody ItemDTO item) {
        return itemService.createItem(item);
    }
    
}
