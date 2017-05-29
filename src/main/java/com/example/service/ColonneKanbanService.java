package com.example.service;

import com.example.dto.ColonneKanbanDTO;


public interface ColonneKanbanService {
	
	/**
	 * Crée une colonne Kanban
	 * @param colonneKanbanDTO informations de la colonne à créer
	 * @param boardId id du tableau dans lequel créer la colonne
	 * @return DTO de la colonne créée
	 */
	ColonneKanbanDTO createColonneKanban(ColonneKanbanDTO colonneKanbanDTO, Long boardId);
	
	/**
	 * Modifie une colonne Kanban
	 * @param id l'id de la colonne à modifier
	 * @param colonne données de la colonne modifiée
	 * @return DTO de la colonne modifiée
	 */
	ColonneKanbanDTO updateColonneKanban(Long id, ColonneKanbanDTO colonne);
	
	/**
	 * Supprime une colonne Kanban
	 * @param id l'id de la colonne à supprimer
	 */
    void deleteColonneKanban(Long id);
}
