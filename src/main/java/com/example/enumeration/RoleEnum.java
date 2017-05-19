package com.example.enumeration;

/**
 * Created by Morgane TROYSI on 18/05/17.
 */

public enum RoleEnum {
    USER("User"),
    ADMIN("Admin");

    private String name = "";

    RoleEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
