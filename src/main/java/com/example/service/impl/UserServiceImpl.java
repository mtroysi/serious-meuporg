package com.example.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.TagDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserStatsDTO;
import com.example.enumeration.StatusEnum;
import com.example.exception.GameMasterException;
import com.example.model.BoardUser;
import com.example.model.TaskUser;
import com.example.model.TaskUserBid;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.transformers.Transformers;

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
        User currentUser = this.getCurrentUser();
        List<User> users = userRepository.findByFirstNameContainingOrLastNameContainingAndEmailNot(query, currentUser.getEmail());
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = transformers.transformUserToUserDto(user);
            result.add(userDTO);
        }
        return result;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        User user = (User)transformers.convertDtoToEntity(userDTO, User.class);
        User userTest = userRepository.findByEmail(user.getEmail());
        if (userTest != null){
        	throw new GameMasterException(ConstanteGameMaster.SIGNUP_ERROR);
        }else{
            // TODO : chiffrer le password
        	user.setId(null);
            user.setDateCreation(Calendar.getInstance().getTime());
            userRepository.save(user);
        }
        return (UserDTO)transformers.convertEntityToDto(user, UserDTO.class);
    }

    @Override
    public User getCurrentUser() {
        String mail = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(mail);
    }
    @Override
    public UserDTO getUser(long id){
    	 return (UserDTO) transformers.convertEntityToDto(userRepository.findOne(id), UserDTO.class);
    }
    
    @Override
    public UserDTO editUser(UserDTO userDTO){
        User user = userRepository.findOne(userDTO.getId());
        if(user!=null && (userDTO.getId()!=null)){
              user.setFirstName(userDTO.getFirstName());
              user.setLastName(userDTO.getLastName());
              userRepository.save(user);
        }
        else{
        	throw new GameMasterException(ConstanteGameMaster.EDIT_ERROR);
        }
      
        return (UserDTO)transformers.convertEntityToDto(user, UserDTO.class);
    }
    
    @Override
    public UserStatsDTO getstats(long id){
    	User user = userRepository.findOne(id);
    	UserStatsDTO userStatsDTO = new UserStatsDTO();
    	Long nbrTaskDone = 0L;
    	Long nbrTaskCreated = 0L;
    	int nbrBoardCreated = 0;
    	Long nbrTaskBid = 0L;
    	
    	User currentUser;
    	
    	List<TaskUser> listTaskUser = user.getTaskUsers();
    	List<BoardUser> listBoardUser= user.getBoardUsers();
    	List<TaskUserBid> listTaskBidUser= user.getTaskUserBids();
    	
    	nbrTaskDone = listTaskUser.stream().filter((TaskUser t)-> StatusEnum.DONE.equals(t.getStatus())).count();
    	nbrTaskCreated =listTaskUser.stream().filter((TaskUser t)-> t.getTask().getCreator().getId().equals(id)).count();
    	nbrBoardCreated = listBoardUser.size();
    	nbrTaskBid = listTaskBidUser.stream().count();
    	userStatsDTO.setNbrTaskCreated(nbrTaskCreated);
    	userStatsDTO.setNbrTaskDone(nbrTaskDone);
    	userStatsDTO.setNbrBoardCreated(nbrBoardCreated);
    	userStatsDTO.setNbrTaskBid(nbrTaskBid);
    	
    	return userStatsDTO;
    }

}
