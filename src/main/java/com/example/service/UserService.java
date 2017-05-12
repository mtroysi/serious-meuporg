package com.example.service;


import antlr.collections.List;
import com.example.dto.UserDTO;

/**
 * Created by sara on 12/05/17.
 */
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    boolean login(UserDTO userDTO);
    //List<UserDTO> listUsers();
}
