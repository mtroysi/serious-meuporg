package com.example.service;

import com.example.dto.ResultDto;

import java.util.List;

/**
 * Created by Morgane TROYSI on 30/05/17.
 */

public interface SearchService {

    /**
     * Cherche les utilisateurs et les tâches dont le nom contiennent la chaîne en paramètre
     * @param query chaîne à chercher
     * @return liste de ResultDTO
     */
    List<ResultDto> searchUsersAndTasks(String query);
}
