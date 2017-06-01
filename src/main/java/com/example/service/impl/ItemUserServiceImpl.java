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
    
    /* 
     * (non-Javadoc)
     * @see com.example.service.ItemUserService#buyItem(java.lang.Long, java.lang.Long)
     */
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
	        			dateEnd.add(Calendar.DAY_OF_YEAR, item.getDuration() != null ? item.getDuration().intValue() : 1);
	        			itemUser.setDateEnd(dateEnd.getTime());
	        			itemUser.setActive(true);
	        			
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
        			itemUser.setActive(false);
        			user.addItemUser(itemUser);
    			}

    			userRepository.save(user);
    			
    			return (ItemDTO)this.transformers.convertEntityToDto(item, ItemDTO.class);
    		}
    	}else{
        	throw new GameMasterException(ConstanteGameMaster.ITEM_NO_FOUND);
    	}
    }

	/* 
	 * (non-Javadoc)
	 * @see com.example.service.ItemUserService#removeItem(java.lang.Long)
	 */
	@Override
	public Boolean removeItem(Long idItem) {
    	User user = userService.getCurrentUser();
    	Item item = itemRepository.findOne(idItem);
    	ItemUser itemUser = user.getItemUser().stream().filter((ItemUser iu) -> iu.getItem().getId().equals(idItem)).findFirst().orElse(null);
    	
    	if(item != null && itemUser != null && !ItemEnum.CURSE.equals(item.getType())){
    		itemUserRepository.deleteByIdItem(item.getId());
    	}else{
        	throw new GameMasterException(ConstanteGameMaster.ITEM_NO_FOUND);
    	}
    	
    	return true;
	}
	

	/* 
	 * (non-Javadoc)
	 * @see com.example.service.ItemUserService#activeItem(java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public Boolean activeItem(Long idItem, Boolean active){
		User user = userService.getCurrentUser();
    	Item item = itemRepository.findOne(idItem);
    	ItemUser itemUser = user.getItemUser().stream().filter((ItemUser iu) -> iu.getItem().getId().equals(idItem)).findFirst().orElse(null);
    	
    	if(item != null && itemUser != null && !ItemEnum.CURSE.equals(item.getType())){
    		// Si c'est un avatar on va modifier l'utilisateur.
    		if(ItemEnum.AVATAR.equals(item.getType())){
    			user.setAvatar(active==false ? null : item.getImage());
    			userRepository.save(user);
    		}
    		
    		//Si c'est pas un Sort alors c'est un type unique, on va donc passer tous les autres en non active
    		if(!ItemEnum.SPELL.equals(item.getType())){ 
        		itemUserRepository.updateNoActiveByType(item.getType().name());
    		}
    		itemUserRepository.updateActiveByIdItem(item.getId(), active);    			
    	}else{
    		throw new GameMasterException(ConstanteGameMaster.ITEM_NO_FOUND);
    	}
    	return true;
	}
}