package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morgane TROYSI on 15/05/17.
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
    User findByEmailAndPassword(String email,String password);

    @Query(value = "SELECT * FROM user WHERE first_name LIKE %?1% AND last_name LIKE %?1% AND email != ?2", nativeQuery = true)
    List<User> findByFirstNameContainingOrLastNameContainingAndEmailNot(String query1, String mail);

}
