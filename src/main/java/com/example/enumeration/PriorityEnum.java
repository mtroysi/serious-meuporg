package com.example.enumeration;

public enum PriorityEnum {

    URGENT_IMPORTANT("Urgent et important"),
    URGENT_NOT_IMPORTANT("Urgent et pas important"),
    NOT_URGENT_IMPORTANT("Pas urgent et important"),
    NOT_URGENT_NOT_IMPORTANT("Pas urgent et pas important");

    private String name = "";

    PriorityEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
