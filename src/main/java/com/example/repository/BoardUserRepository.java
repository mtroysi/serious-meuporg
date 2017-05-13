package com.example.repository;

import com.example.model.Board;
import com.example.model.BoardUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@Repository
public interface BoardUserRepository extends PagingAndSortingRepository<BoardUser, Long> {

    /**
     * Get list of board by user_id
     * @param user_id
     * @return
     */
    List<BoardUser> findAllByUserId(Long user_id);
}
