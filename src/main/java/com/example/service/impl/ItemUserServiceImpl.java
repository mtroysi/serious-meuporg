package com.example.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.ItemDTO;
import com.example.enumeration.ItemEnum;
import com.example.enumeration.TypeNotifEnum;
import com.example.exception.GameMasterException;
import com.example.model.Item;
import com.example.model.ItemUser;
import com.example.model.Notification;
import com.example.model.User;
import com.example.repository.ItemRepository;
import com.example.repository.ItemUserRepository;
import com.example.repository.UserRepository;
import com.example.service.ItemUserService;
import com.example.service.UserService;
import com.example.transformers.Transformers;


@Service
public class ItemUserServiceImpl implements ItemUserService {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemUserRepository itemUserRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Transformers transformers;
    
    @Override
    public ItemDTO buyItem(Long idItem, Long idUser){
    	User user = userService.getCurrentUser();
    	Item item = itemRepository.findOne(idItem);
    	
    	if(item != null ){
        	//On regarde si l'utilisateur a les moyens d'acheter l'item
    		if(item.getPrice() > user.getMoney() || item.getRequiredLevel() > user.getLevel()){
            	throw new GameMasterException(ConstanteGameMaster.ITEM_NO_RESSOURCE);
    		}else{
    			user.setMoney(user.getMoney() - item.getPrice());
    			
    			ItemUser itemUser = new ItemUser();
    			itemUser.setItem(item);
    			
    			//On regarde si c'est une malédiction
    			if(ItemEnum.CURSE.equals(item.getType()) && idUser != null){
    				User userMal = userRepository.findOne(idUser);
    				//Si malediction alors on va ajouter l'objet sur l'utilisateur en parametre
    				if(userMal != null){
	    				Notification notif = new Notification();
	    				notif.setContent(ConstanteGameMaster.BUY_CURSE + user.getFirstName());
	    				notif.setTitle(ConstanteGameMaster.BUY_ITEM);
	    				notif.setType(TypeNotifEnum.important);
	    				notif.setIsRead(false);
	    				notif.setDateCreation(new Date());
	    				notif.setUser(userMal);
	    				userMal.addNotification(notif);

	        			// Ajout d'une duree de malediction
	        			Calendar dateEnd = new GregorianCalendar();
	        			dateEnd.add(Calendar.DAY_OF_YEAR, item.getDuration());
	        			itemUser.setDateEnd(dateEnd.getTime());
	        			
	        			// Add item
	        			itemUser.setUser(userMal);
	        			userMal.addItemUser(itemUser);
	        			userRepository.save(userMal);
    				}
    			}
    			else{
    				Notification notif = new Notification();
    				notif.setContent(item.getName());
    				notif.setTitle(ConstanteGameMaster.BUY_ITEM);
    				notif.setType(TypeNotifEnum.important);
    				notif.setIsRead(false);
    				notif.setDateCreation(new Date());
    				notif.setUser(user);
    				user.addNotification(notif);
    				
    				// Add item
        			itemUser.setUser(user);
        			user.addItemUser(itemUser);
    			}

    			userRepository.save(user);
    			
    			return (ItemDTO)this.transformers.convertEntityToDto(item, ItemDTO.class);
    		}
    	}else{
        	throw new GameMasterException(ConstanteGameMaster.ITEM_NO_FOUND);
    	}
    }
}