package com.example.enumeration;

public enum PeriodicityEnum {

    DAILY("Jour"),
    WEEKLY("Semaine"),
    MONTHLY("Mois"),
    YEARLY("Année");

    private String name = "";

    PeriodicityEnum(String s) {
        this.name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : this.name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
