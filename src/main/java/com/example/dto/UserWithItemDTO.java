package com.example.dto;

import java.util.ArrayList;
import java.util.List;


public class UserWithItemDTO extends UserDTO {

    private List<ItemUserDTO> itemUser = new ArrayList<>();

	public List<ItemUserDTO> getItemUser() {
		return itemUser;
	}

	public void setItemUser(List<ItemUserDTO> itemUser) {
		this.itemUser = itemUser;
	}
}
