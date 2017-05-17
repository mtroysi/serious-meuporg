package com.example.controller;

import com.example.dto.UserDTO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> loadUsers(@RequestParam("query") String query) {
        return userService.loadUsers(query);
    }
}
