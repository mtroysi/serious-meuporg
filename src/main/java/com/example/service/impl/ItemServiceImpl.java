package com.example.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.ItemDTO;
import com.example.enumeration.ItemEnum;
import com.example.exception.GameMasterException;
import com.example.model.Item;
import com.example.model.ItemUser;
import com.example.model.User;
import com.example.repository.ItemRepository;
import com.example.repository.ItemUserRepository;
import com.example.service.ItemService;
import com.example.service.UserService;
import com.example.transformers.Transformers;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemUserRepository itemUserRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private Transformers transformers;

    /* 
     * (non-Javadoc)
     * @see com.example.service.ItemService#getAllItemsByUser(java.lang.Long)
     */
    @Override
    public List<ItemDTO> getAllItemsByUser(Long userId) {
        List<Item> items = (List<Item>)itemRepository.findAll();
        List<ItemUser> itemsUser = itemUserRepository.findByUserId(userId);
        
        return items.stream().map(item -> {
        	ItemUser itemU = itemsUser.stream().filter((ItemUser element) -> element.getItem().getId().equals(item.getId())).findFirst().orElse(null);
        	// Nous ne récupérons que les objets que l'utilisateur n'a pas achetés ou les malédictions
        	if(itemU == null || ItemEnum.CURSE.equals(itemU.getItem().getType())){
        		return (ItemDTO)transformers.convertEntityToDto(item, ItemDTO.class);
        	}else{
        		return null;
        	}
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }

    
	/* 
	 * (non-Javadoc)
	 * @see com.example.service.ItemService#createItem(com.example.dto.ItemDTO)
	 */
	@Override
	public ItemDTO createItem(ItemDTO item) {
		User creator = userService.getCurrentUser();
		if(creator.getIsSuperAdmin()){
			Item newItem = (Item)transformers.convertDtoToEntity(item, Item.class);
			return (ItemDTO)transformers.convertEntityToDto(itemRepository.save(newItem), ItemDTO.class);
		}else{
			throw new GameMasterException(ConstanteGameMaster.UNAUTHORIZED_ERROR);
		}
	}
}
