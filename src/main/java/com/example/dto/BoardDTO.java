package com.example.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */
public class BoardDTO {
    private Long id;
    private String name;
    private String color;
    private List<UserDTO> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
