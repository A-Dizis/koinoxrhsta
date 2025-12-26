package com.angelos.koinoxrhsta.impl.enums;

/**
 * Available roles in the application.
 * 
 * <p> <b>NOTE:</b> Names with capital letter and dash should be 
 * used in order to avoid unpredicted behavior in Vaadin
 * security in page.
 */
public enum RoleGroup {
    
	SUPER_USER("SUPER_USER"),

    ADMIN("ADMIN"),

	USER_TYPE_1("USER_TYPE_1");
	
	private RoleGroup(String name) {};
	
	@Override
	public String toString() {
		return this.name();
	}	
}
