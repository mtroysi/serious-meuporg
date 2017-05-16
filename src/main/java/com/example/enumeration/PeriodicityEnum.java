package com.example.enumeration;

public enum PeriodicityEnum {
	
	DAILY("Jour"),
	MONTHLY("Mois"), 
	ANNUAL("Année");

	private final String name;

	private PeriodicityEnum(String s) {
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
