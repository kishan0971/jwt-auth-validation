package com.in2it.jwtauthenticationandauthorization.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
	
	SUPER_ADMIN_READ("super_admin:read"),
	SUPER_ADMIN_CREATE("super_admin:create"),
	SUPER_ADMIN_UPDATE("super_admin:update"),
	SUPER_ADMIN_DEELTE("super_admin:delete"),
	ADMIN_READ("admin:read"),
	ADMIN_CREATE("admin:create"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_DEELTE("admin:delete"),
	EMPLOYEE_READ("employee:read"),
	EMPLOYEE_UPDATE("employee:update"),
	EMPLOYEE_CREATE("employee:update"),
	EMPLOYEE_DELETE("employee:delete"),
	TRAINEE_READ("trainee:read"),
	TRAINEE_CREATE("trainee:update"),
	TRAINEE_UPDATE("trainee:update"),
	TRAINEE_DELETE("trainee:delete")
	
	
	;
	
	
	@Getter
	private final String permission;

}
