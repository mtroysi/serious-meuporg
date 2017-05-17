package com.example.enumeration;


public enum StatusEnum {

    TODO("Todo"),
    IN_PROGRESS("In progress"),
    DONE("Done"),
    DONE_ADMIN("Done avec validation de l'administateur");

    private String name = "";

    StatusEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
