package com.example.controller;

import com.example.dto.ItemDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Morgane TROYSI on 22/05/17.
 */

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<ItemDto> getInventory(@PathVariable("id") Long id){
        return userService.getUserInventory(id);
    }
}
