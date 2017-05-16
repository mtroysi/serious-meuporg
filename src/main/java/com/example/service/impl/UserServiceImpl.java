package com.example.service.impl;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.transformers.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Transformers transformers;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> loadUsers(String query) {
        List<User> users = userRepository.findByFirstNameContainingOrLastNameContaining(query, query);
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = (UserDTO) transformers.convertEntityToDto(user, UserDTO.class);
            userDTO.setFullName(user.getFirstName() + ' ' + user.getLastName());
            result.add(userDTO);
        }
        return result;
    }
}
