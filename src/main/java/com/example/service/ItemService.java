package com.example.service;

import com.example.dto.ItemDTO;

import java.util.List;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */
public interface ItemService {
	
	/**
     * Retourne la liste de tous les items
     * @return la liste de tous les items
     */
    List<ItemDTO> getAllItemsByUser(Long idUser);
    
    /**
     * Création d'un item
     * @return Item créé
     */
    ItemDTO createItem(ItemDTO item);
}
