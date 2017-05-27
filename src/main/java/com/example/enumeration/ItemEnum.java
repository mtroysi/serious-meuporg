package com.example.enumeration;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */
public enum ItemEnum {
    WALLPAPER("wallpaper"),
    AVATAR("avatar"),
    SPELL("spell"),
    CURSE("curse");

    private String name = "";

    ItemEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
