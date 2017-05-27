package com.example.service;

import com.example.dto.ItemDTO;

import java.util.List;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */
public interface ItemService {

    List<ItemDTO> getAllItemsByUser(Long idUser);
}
