package com.example.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.ItemDTO;
import com.example.dto.ItemUserDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserRankinDTO;
import com.example.dto.UserStatsDTO;
import com.example.dto.UserWithItemDTO;
import com.example.enumeration.StatusEnum;
import com.example.exception.GameMasterException;
import com.example.model.BoardUser;
import com.example.model.Item;
import com.example.model.ItemUser;
import com.example.model.TaskUser;
import com.example.model.TaskUserBid;
import com.example.model.User;
import com.example.repository.ItemRepository;
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

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Retourne les utilisateurs dont le nom + prénom contiennent la chaîne en paramètre, utilisateur connecté exclu
     * @param query chaîne à chercher
     * @return liste des utilisateurs dont le nom + prénom contiennent la chaîne en paramètre, utilisateur connecté exclu
     */
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

    /**
     * Crée un utilisateur
     * @param userDTO données de l'utilisateur à créer
     * @return
     */
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
            user.setExperience(0L);
            user.setLevel(0L);
            user.setMoney(0L);
            userRepository.save(user);
        }
        return (UserDTO)transformers.convertEntityToDto(user, UserDTO.class);
    }

    /**
     * Retourne l'utilisateur connecté
     * @return l'utilisateur connecté
     */
    @Override
    public User getCurrentUser() {
        String mail = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(mail);
    }

    /**
     * Retourne l'utilisateur dont l'id est passé en paramètre
     * @param id l'id de l'utilisateur
     * @return l'utilisateur correspondant
     */
    @Override
    public UserWithItemDTO getUser(long id){
    	 User user = userRepository.findOne(id);
    	 UserWithItemDTO userWithItemDto = (UserWithItemDTO) transformers.convertEntityToDto(user, UserWithItemDTO.class);
    	 userWithItemDto.setItemUser(user.getItemUser().stream().map((ItemUser iu) -> {
         	ItemUserDTO itemuserdto = (ItemUserDTO) this.transformers.convertEntityToDto(iu.getItem(), ItemUserDTO.class);
         	itemuserdto.setDateEnd(iu.getDateEnd());
         	itemuserdto.setActive(iu.getActive());
         	return itemuserdto;
         }).collect(Collectors.toList()));
    	 return userWithItemDto;
    }
    
    /**
     * Retourne la liste de tous les utilisateurs
     * @return liste des utilisateurs
     */
    @Override
    public List<UserDTO> getAllUser(){
    	List<User> listUser = (List<User>) userRepository.findAll();
    	return listUser.stream().map((User user) -> (UserDTO) this.transformers.convertEntityToDto(user, UserDTO.class)).collect(Collectors.toList());
    }

    /**
     * Modifie un utilisateur
     * @param userDTO données de l'utilisateur à modifier
     * @return DTO de l'utilisateur modifié
     */
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
    public UserStatsDTO getStats(Long id){
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
    
    @Override
    public UserRankinDTO getRankin(long id){
    	
    	User user = userRepository.findOne(id);
    	UserRankinDTO userRankinDto = new UserRankinDTO();
    	Long rankLevel = 0L;
    	Long rankMoney = 0L;
    	Long rankExperience = 0L;
    	Long nbrUsers = 0L;
    	List<User> listUser = (List<User>) userRepository.findAll();

    	
    	nbrUsers= (long) listUser.size();
    	rankLevel = listUser.stream().filter((User u)-> u.getLevel()>user.getLevel()).count()+1;
    	rankMoney = listUser.stream().filter((User u)-> u.getMoney()>user.getMoney()).count()+1;
    	rankExperience = listUser.stream().filter((User u)-> u.getExperience()>user.getExperience()).count()+1;
    	userRankinDto.setNbrUsers(nbrUsers);
    	userRankinDto.setRankExperience(rankExperience);
    	userRankinDto.setRankLevel(rankLevel);
    	userRankinDto.setRankMoney(rankMoney);
    	
    	return userRankinDto;
    	
    }

    /**
     * Retourne l'inventaire d'un utilisateur
     * @param id l'id de l'utilisateur
     * @return liste des items possédés par l'utilisateur
     */
    @Override
    public List<ItemUserDTO> getUserInventory(Long id) {
        User user = userRepository.findOne(id);
        return user.getItemUser().stream().map((ItemUser item) -> {
        	ItemUserDTO itemuserdto = (ItemUserDTO) this.transformers.convertEntityToDto(item.getItem(), ItemUserDTO.class);
        	itemuserdto.setDateEnd(item.getDateEnd());
        	itemuserdto.setActive(item.getActive());
        	return itemuserdto;
        }).collect(Collectors.toList());
    }

    /**
     * Modifie l'inventaire d'un utilisateur
     * @param itemDtoList liste des objets possédés par l'utilisateur
     * @return inventaire mis à jour
     */
    @Override
    public List<ItemDTO> updateInventory(List<ItemDTO> itemDtoList) {
        User user = this.getCurrentUser();
        List<Item> items = new ArrayList<>();

        itemDtoList.stream().forEach(itemDto -> items.add((Item)this.transformers.convertDtoToEntity(itemDto, Item.class)));
        //user.setInventory(items);
        user = userRepository.save(user);

        itemDtoList.clear();
        //user.getInventory().stream().forEach(item -> itemDtoList.add((ItemDto)this.transformers.convertEntityToDto(item, ItemDto.class)));
        return itemDtoList;
    }
    
    public void manageMoneyExpUser(User user, Integer money, Integer exp){
    	user.setMoney(user.getMoney() + money);
    	user.setExperience(user.getExperience() + exp);
    	
    	// Calculation of the next level
    	if((user.getLevel()+1) * ConstanteGameMaster.XP <= user.getExperience()){
    		user.setLevel(user.getLevel() + 1);
    	}
    }
}
