package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

/**
 * Created by Morgane TROYSI on 15/05/17.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	/**
	 * Retorune un utilisateur par rapport à son email
	 * @param email
	 * @return utilisateur
	 */
    User findByEmail(String email);
    
    /**
     * Retourne un utilisateur par rapport a son email et son mot de passe
     * @param email
     * @param password
     * @return utilisateur
     */
    User findByEmailAndPassword(String email,String password);
    
    /**
     * Retourne la liste des utilisateurs dont le nom + prénom contiennent la chaîne en paramètre, utilisateur connecté exclu (mail)
     * @param query1
     * @param mail
     * @return liste utilisateur
     */
    @Query(value = "SELECT * FROM user WHERE (first_name LIKE %?1% OR last_name LIKE %?1%) AND email != ?2", nativeQuery = true)
    List<User> findByFirstNameContainingOrLastNameContainingAndEmailNot(String query1, String mail);

    /**
     * Retourne la liste des utilisateurs dont le nom + prénom contiennent la chaîne en paramètre
     * @param query
     * @return liste utilisateur
     */
    @Query(value = "SELECT * FROM user WHERE first_name LIKE %?1% OR last_name LIKE %?1%", nativeQuery = true)
    List<User> findByFirstNameContainingOrLastNameContaining(String query);

    /**
     * Retourne la liste des utilisateurs du tableau dont le nom + prénom contiennent la chaîne en paramètre
     *
     * @param id
     * @param query
     * @return liste utilisateur
     */
    @Query(value = "select * from user as u where (first_name LIKE %?2% OR last_name LIKE %?2%) AND u.id in (select bu.user_id from board_user as bu where bu.board_id = ?1)", nativeQuery = true)
    List<User> findByIdAndFirstNameContainingOrLastNameContaining(Long id, String query);
}
