package com.example.repository;

import com.example.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morgane TROYSI on 15/05/17.
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findByFirstNameContainingOrLastNameContaining(String query1, String query2);
}
