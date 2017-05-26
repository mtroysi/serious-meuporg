package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.ItemDTO;
import com.example.exception.GameMasterException;
import com.example.model.Item;
import com.example.model.User;
import com.example.repository.ItemRepository;
import com.example.repository.ItemUserRepository;
import com.example.service.ItemUserService;
import com.example.service.UserService;
import com.example.transformers.Transformers;


@Service
public class ItemUserServiceImpl implements ItemUserService {

    @Autowired
    private ItemRepository itemRepository;

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
        	throw new GameMasterException(ConstanteGameMaster.ITEM_NO_RESSOURCE);
    	}else{
        	throw new GameMasterException(ConstanteGameMaster.ITEM_NO_FOUND);
    	}
    }
}