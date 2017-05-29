package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.dto.UserRankinDTO;
import com.example.dto.UserStatsDTO;
import com.example.dto.UserWithItemDTO;
import com.example.service.UserService;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Crée un utilisateur.
     * @param userDTO données de l'utilisateur à créer
     * @return UserDTO
     */
    @RequestMapping(method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    /**
     * Modifie un utilisateur.
     * @param userDTO données modifiées de l'utilisateur
     * @return utilisateur modifié
     */
    @RequestMapping(method = RequestMethod.PUT)
    public UserDTO editUser(@RequestBody UserDTO userDTO){
        return userService.editUser(userDTO);
    }

    /**
     * Retourne la liste des utilisateurs dont le nom contiennent la chaîne en paramètre.
     * @param query chaîne à chercher
     * @return liste des utilisateurs correspondants
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> loadUsers(@RequestParam("query") String query) {
        return userService.loadUsers(query);
    }
    
    /**
     * Retourne la liste complete des utilisateurs de l'application
     * @return liste des utilisateurs
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }
    

    /**
     * Retourne l'utilisateur dont l'id est passé en paramètre.
     * @param id id de l'utilisateur
     * @return l'utilisateur correspondant
     */     
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserWithItemDTO getUser(@PathVariable ("id") Long id){
        return userService.getUser(id);
    }

    /**
     * Retourne les statistiques d'un utilisateur
     * @param id id de l'utilisateur
     * @return UserStatsDTO
     */
    @RequestMapping(value = "/{id}/stats",method = RequestMethod.GET)
    public UserStatsDTO getStats(@PathVariable ("id") Long id){
        return userService.getStats(id);
    }

    /**
     * Retourne les rangs d'un utilisateur
     * @param id id de l'utilisateur
     * @return UserRankinDTO
     */
    @RequestMapping(value = "/{id}/rankin",method = RequestMethod.GET)
    public UserRankinDTO getRankin(@PathVariable ("id") Long id){
        return userService.getRankin(id);
    }
}
