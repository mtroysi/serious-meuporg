package com.example.enumeration;


public enum ItemPositionEnum {
	TOP_RIGHT("En haut à droite"),
	TOP_LEFT("En haut à gauche"),
	BOTTOM_RIGHT("En bas à droite"),
	BOTTOM_LEFT("En bas à gauche"),
	FULL("Toute la page");

    private String name = "";

    ItemPositionEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
