package com.angelos.koinoxrhsta.impl.enums;

public enum Sex {
	
	MALE("Male"),
	
	FEMALE("Female");
	
	private Sex(String name) {
		
	};
	
	@Override
	public String toString() {
		return this.name();
	}	
}
