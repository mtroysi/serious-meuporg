package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Board;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@Repository
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {

}
