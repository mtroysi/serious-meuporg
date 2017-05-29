package com.example.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.BoardUser;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */
@Repository
public interface BoardUserRepository extends PagingAndSortingRepository<BoardUser, Long> {

    /**
     * Retourne la liste des tableaux par utilisateur
     * @param user_id : id de l'utilisateur
     * @return liste de BoardUser
     */
    List<BoardUser> findAllByUserId(Long user_id);
}
