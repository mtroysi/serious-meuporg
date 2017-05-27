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

    /**
     * Retourne l'inventaire d'un utilisateur.
     * @param id id de l'utilisateur
     * @return liste des objets possédés par l'utilisateur
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<ItemDto> getInventory(@PathVariable("id") Long id){
        return userService.getUserInventory(id);
    }

    /**
     * Modifie l'inventaire d'un utilisateur
     * @param items contenu mis à jour de l'inventaire
     * @return inventaire mis à jour
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<ItemDto> updateInventory(@RequestBody List<ItemDto> items){
        return userService.updateInventory(items);
    }
}
