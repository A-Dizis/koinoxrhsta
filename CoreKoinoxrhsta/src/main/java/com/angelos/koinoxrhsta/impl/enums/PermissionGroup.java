package com.angelos.koinoxrhsta.impl.enums;

public enum PermissionGroup {
    
    ADMIN("ADMIN"),

	USER_TYPE_1("USER_TYPE_1");
	
	private PermissionGroup(String name) {};
	
	@Override
	public String toString() {
		return this.name();
	}	
}
