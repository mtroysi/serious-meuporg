package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ItemDTO;
import com.example.dto.ItemUserDTO;
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

    /**
     * Retourne l'inventaire d'un utilisateur.
     * @param id id de l'utilisateur
     * @return liste des objets possédés par l'utilisateur
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<ItemUserDTO> getInventory(@PathVariable("id") Long idUser){
        return userService.getUserInventory(idUser);
    }

    /**
     * Modifie l'inventaire d'un utilisateur
     * @param items contenu mis à jour de l'inventaire
     * @return inventaire mis à jour
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<ItemDTO> updateInventory(@RequestBody List<ItemDTO> items){
        return userService.updateInventory(items);
    }
    
    /**
     * Supprime un item de l'inventaire
     * @param idItem
     * @return
     */
    @RequestMapping(value = "/{idItem}", method = RequestMethod.DELETE)
    public Boolean removeItem(@PathVariable("idItem") Long idItem) {
        return itemUserService.removeItem(idItem);
    }
    
    /**
     * Active un item de l'inventaire
     * @param idItem
     * @param active : boolean
     * @return
     */
    @RequestMapping(value = "/{idItem}/active", method = RequestMethod.PUT)
    public Boolean activeItem(@PathVariable("idItem") Long idItem, @RequestBody Boolean active) {
        return itemUserService.activeItem(idItem, active);
    }
    
    /**
     * Achete et donc ajoute un item dans l'inventaire
     * @param idItem
     * @param idUser
     * @return
     */
    @RequestMapping(value = "/{idItem}", method = RequestMethod.POST)
    public ItemDTO buyItem(@PathVariable("idItem") Long idItem, @RequestBody(required=false) Long idUser) {
        return itemUserService.buyItem(idItem, idUser);
    }
}
