package com.example.enumeration;


public enum PriorityEnum {

    URGENT_IMPORTANT("Urgent and important"),
    URGENT_NOT_IMPORTANT("Urgent and not important"),
    NOT_URGENT_IMPORTANT("Not urgent and important"),
    NOT_URGENT_NOT_IMPORTANT("Not urgent and not important");

    private String name = "";

    PriorityEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
