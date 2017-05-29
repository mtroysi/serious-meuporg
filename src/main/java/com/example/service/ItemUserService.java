package com.example.service;

import com.example.dto.ItemDTO;

public interface ItemUserService {
	
	/**
	 * Ajoute un item a l'inventaire
	 * @param idItem : id item
	 * @param idUser : id utilisateur
	 * @return item ajouté
	 */
	ItemDTO buyItem(Long idItem, Long idUser);
	
	/**
	 * Supprime un item de l'inventaire
	 * @param idItem : id item
	 * @return
	 */
	Boolean removeItem(Long idItem);
	
	/**
	 * Active ou desactive un item dans l'inventaire
	 * @param idItem : id item
	 * @param active : boolean active
	 * @return
	 */
	Boolean activeItem(Long idItem, Boolean active);
}
