package com.example.repository;

import com.example.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sara on 12/05/17.
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
    User findByEmailAndPassword(String email,String password);

}
