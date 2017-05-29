package com.example.service;

import java.util.List;

import com.example.dto.ItemDTO;
import com.example.dto.ItemUserDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserRankinDTO;
import com.example.dto.UserStatsDTO;
import com.example.dto.UserWithItemDTO;
import com.example.model.User;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

public interface UserService {
	
	/**
     * Crée un utilisateur
     * @param userDTO données de l'utilisateur à créer
     * @return
     */
    UserDTO createUser(UserDTO userDTO);
    
    
    /**
     * Modifie un utilisateur
     * @param userDTO données de l'utilisateur à modifier
     * @return DTO de l'utilisateur modifié
     */
    UserDTO editUser(UserDTO userDTO);
    
    
    /**
     * Retourne les utilisateurs dont le nom + prénom contiennent la chaîne en paramètre, utilisateur connecté exclu
     * @param query chaîne à chercher
     * @return liste des utilisateurs dont le nom + prénom contiennent la chaîne en paramètre, utilisateur connecté exclu
     */
    List<UserDTO> loadUsers(String query);
    
    
    /**
     * Retourne l'utilisateur connecté
     * @return l'utilisateur connecté
     */
    User getCurrentUser();
    
    
    /**
     * Retourne l'utilisateur dont l'id est passé en paramètre
     * @param id l'id de l'utilisateur
     * @return l'utilisateur correspondant
     */
    UserWithItemDTO getUser(long id);
    
    
    /**
     * Retourne la liste de tous les utilisateurs
     * @return liste des utilisateurs
     */
    List<UserDTO> getAllUser();
    
    
    /**
     * Retorune les statistiques d'un utilisateur
     * @param id
     * @return statistiques
     */
    UserStatsDTO getStats(Long id);
    
    
    /**
     * Retourne le classement d'un utilisateur par rapport aux autres utilisateurs
     * @param id
     * @return
     */
    UserRankinDTO getRankin(long id);
    
    
    /**
     * Retourne l'inventaire d'un utilisateur
     * @param id l'id de l'utilisateur
     * @return liste des items possédés par l'utilisateur
     */
    List<ItemUserDTO> getUserInventory(Long id);
    
    
    /**
     * Modifie l'inventaire d'un utilisateur
     * @param itemDtoList liste des objets possédés par l'utilisateur
     * @return inventaire mis à jour
     */
    List<ItemDTO> updateInventory(List<ItemDTO> items);
    
    
    /**
     * Gestionnaire de l'expérience et de l'argent d'un utilisateur
     * @param user : utilisateur
     * @param money : valeur a ajouter
     * @param exp : valeur a ajouter
     */
    void manageMoneyExpUser(User user, Integer money, Integer exp);
}
