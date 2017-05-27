package com.example.service;

import java.util.List;

import com.example.dto.ItemDTO;
import com.example.dto.ItemUserDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserRankinDTO;
import com.example.dto.UserStatsDTO;
import com.example.dto.UserWithItemDTO;
import com.example.model.User;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO editUser(UserDTO userDTO);

    List<UserDTO> loadUsers(String query);

    User getCurrentUser();

    UserWithItemDTO getUser(long id);

    List<UserDTO> getAllUser();
    
    UserStatsDTO getStats(Long id);
    
    UserRankinDTO getRankin(long id);

    List<ItemUserDTO> getUserInventory(Long id);

    List<ItemDTO> updateInventory(List<ItemDTO> items);
    
    void manageMoneyExpUser(User user, Integer money, Integer exp);
}
