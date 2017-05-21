package com.example.enumeration;



public enum TypeNotifEnum {
    error("Erreur"),
    important("Important"),
    information("Information");

    private String name = "";

    TypeNotifEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
