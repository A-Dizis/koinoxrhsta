package com.angelos.koinoxrhsta.impl.enums;

public enum PermissionGroup {
    
    ADMIN("Admin"),

	USER_TYPE_1("User Type 1");
	
	private PermissionGroup(String name) {};
	
	@Override
	public String toString() {
		return this.name();
	}	
}
