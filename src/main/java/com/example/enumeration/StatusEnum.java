package com.example.enumeration;


public enum StatusEnum {

    TODO("Todo"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private String name = "";

    StatusEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
