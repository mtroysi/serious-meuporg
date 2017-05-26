package com.example.service;

import com.example.dto.ItemDto;
import com.example.dto.UserDTO;
import com.example.dto.UserRankinDTO;
import com.example.dto.UserStatsDTO;
import com.example.model.User;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO editUser(UserDTO userDTO);

    List<UserDTO> loadUsers(String query);

    User getCurrentUser();

    UserDTO getUser(long id);

    List<UserDTO> getAllUser();
    
    UserStatsDTO getstats(long id);
    
    UserRankinDTO getRankin(long id);

    List<ItemDto> getUserInventory(Long id);

    List<ItemDto> updateInventory(List<ItemDto> items);
    
    void manageMoneyExpUser(User user, Integer money, Integer exp);
}
