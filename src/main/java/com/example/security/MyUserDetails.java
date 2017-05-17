package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserDetails extends User {
	
	private final long idUser;

	public MyUserDetails(String username, String password, boolean enabled,
	        boolean accountNonExpired, boolean credentialsNonExpired,
	        boolean accountNonLocked,long idUser,
	        Collection<? extends GrantedAuthority> authorities) {
	    super(username, password, enabled, accountNonExpired,
	            credentialsNonExpired, accountNonLocked, authorities);
	    // TODO Auto-generated constructor stub
	    this.idUser = idUser;
	}
	
	public long getIdUser(){
		return idUser;
	}
}
