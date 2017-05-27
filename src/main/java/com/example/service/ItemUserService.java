package com.example.service;

import com.example.dto.ItemDTO;

public interface ItemUserService {

	ItemDTO buyItem(Long idItem, Long idUser);

	Boolean removeItem(Long idItem);
}
