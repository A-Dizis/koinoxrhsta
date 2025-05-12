package com.angelos.koinoxrhsta.impl.enums;

public enum Side {
	
	FRONT("Front side"),
	
	BACK("Back side"),
	
	RIGHT("Right side"),
	
	LEFT("Left side");
	
	private Side(String name) {
		
	};
	
	@Override
	public String toString() {
		return this.name();
	}	
}
