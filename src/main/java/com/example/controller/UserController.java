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
import com.example.service.UserService;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> loadUsers(@RequestParam("query") String query) {
        return userService.loadUsers(query);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable ("id") Long id){
        return userService.getUser(id);
    }
}
