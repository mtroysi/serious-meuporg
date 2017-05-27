package com.example.controller;

import com.example.dto.ItemDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserRankinDTO;
import com.example.dto.UserStatsDTO;
import com.example.dto.UserWithItemDTO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public UserDTO editUser(@RequestBody UserDTO userDTO){
        return userService.editUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> loadUsers(@RequestParam("query") String query) {
        return userService.loadUsers(query);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserWithItemDTO getUser(@PathVariable ("id") Long id){
        return userService.getUser(id);
    }
    @RequestMapping(value = "/{id}/stats",method = RequestMethod.GET)
    public UserStatsDTO getstats(@PathVariable ("id") Long id){
        return userService.getstats(id);
    }
    
    @RequestMapping(value = "/{id}/rankin",method = RequestMethod.GET)
    public UserRankinDTO getRankin(@PathVariable ("id") Long id){
        return userService.getRankin(id);
    }
}
