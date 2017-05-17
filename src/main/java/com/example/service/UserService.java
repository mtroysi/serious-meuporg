package com.example.service;

import com.example.dto.UserDTO;

import java.util.List;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

public interface UserService {
    List<UserDTO> loadUsers(String query);
}
