package com.example.repository;

import com.example.model.Board;
import com.example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@Repository
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {

}
