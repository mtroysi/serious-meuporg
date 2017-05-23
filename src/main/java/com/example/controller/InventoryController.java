package com.example.controller;

import com.example.dto.ItemDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.PUT)
    public List<ItemDto> updateInventory(@RequestBody List<ItemDto> items){
        return userService.updateInventory(items);
    }
}
