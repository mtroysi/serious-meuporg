package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ItemDTO;
import com.example.service.ItemUserService;
import com.example.service.UserService;

/**
 * Created by Morgane TROYSI on 22/05/17.
 */

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    UserService userService;

    @Autowired
    ItemUserService itemUserService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<ItemDTO> getInventory(@PathVariable("id") Long idUser){
        return userService.getUserInventory(idUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<ItemDTO> updateInventory(@RequestBody List<ItemDTO> items){
        return userService.updateInventory(items);
    }
    
    @RequestMapping(value = "/{idItem}", method = RequestMethod.POST)
    public ItemDTO buyItem(@PathVariable("idItem") Long idItem, @RequestBody Long idUser) {
        return itemUserService.buyItem(idItem, idUser);
    }
}
