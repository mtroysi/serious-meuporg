package com.example.service;

import com.example.dto.BoardWithDetailDTO;

import java.util.List;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */
public interface BoardUserService {

	 /**
     * Retourne les tableaux d'un utilisateur donné
     * @param user_id l'id de l'utilisateur
     * @return la liste des tableaux de l'utilisateur
     */
    List<BoardWithDetailDTO> getListBoardByUserId(Long user_id);
}
