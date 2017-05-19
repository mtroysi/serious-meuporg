package com.example.enumeration;



public enum TypeNotifEnum {
    ERROR("Erreur"),
    IMPORTANT("Important"),
    INFORMATION("Information");

    private String name = "";

    TypeNotifEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
