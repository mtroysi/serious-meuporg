package com.example.service;

import com.example.dto.UserDTO;

import java.util.List;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> loadUsers(String query);
}
