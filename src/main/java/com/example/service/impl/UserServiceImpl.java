package com.example.service.impl;

import com.example.ConstanteGameMaster;
import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.transformers.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.*;
import java.util.Calendar;

/**
 * Created by sara on 12/05/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Transformers transformers;

    @Override
    public UserDTO createUser(UserDTO userDTO){
        User user = (User)transformers.convertDtoToEntity(userDTO, User.class);
        User userTest = userRepository.findByEmail(user.getEmail());
        if (userTest != null){
        	throw new GameMasterException(ConstanteGameMaster.SIGNUP_ERROR);
        }else{
            // TO DO : chiffrer le password
        	user.setId(null);
            user.setDateCreation(Calendar.getInstance().getTime());
            userRepository.save(user);
        }
        return (UserDTO)transformers.convertEntityToDto(user, UserDTO.class);
    }
}
