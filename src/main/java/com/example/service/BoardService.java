package com.example.service;

import com.example.dto.BoardDTO;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */
public interface BoardService {
	
	/**
     * Crée un tableau
     * @param boardDTO informations nécessaires à la création du tableau
     * @return le DTO du tableau créé
     */
    BoardDTO createBoard(BoardDTO boardDTO);
    
    /**
     * Met à jour un tableau
     * @param boardDTO données du tableau modifié
     * @return le DTO du tableau modifié
     */
    BoardDTO updateBoard(BoardDTO boardDTO);

    /**
     * Supprime un tableau
     * @param id l'id du tableau à supprimer
     */
    void deleteBoard(Long id);

    /**
     * Retourne le tabbleau dont l'id est passé en paramètre
     * @param id l'id du tableau
     * @return le DTO du tableau correspondant
     */
    BoardDTO getBoard(Long id);
}
