package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sara on 12/05/17.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    // return true : il peut se connecter, false sinon
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login (@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }
}
