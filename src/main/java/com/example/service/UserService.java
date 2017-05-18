package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> loadUsers(String query);
    User getCurrentUser();
    UserDTO getUser(long id);
}
