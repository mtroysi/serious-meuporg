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
	 * @return etat de la suppression
	 */
	Boolean removeItem(Long idItem);
	
	/**
	 * Active ou desactive un item dans l'inventaire
	 * @param idItem : id item
	 * @param active : boolean active
	 * @return etat de la modification
	 */
	Boolean activeItem(Long idItem, Boolean active);
}
