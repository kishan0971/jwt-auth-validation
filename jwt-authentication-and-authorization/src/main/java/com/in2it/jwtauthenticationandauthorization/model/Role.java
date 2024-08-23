package com.in2it.jwtauthenticationandauthorization.model;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {


	
	SUPER_ADMIN(
			Set.of(
					Permission.SUPER_ADMIN_CREATE,
					Permission.SUPER_ADMIN_READ,
					Permission.SUPER_ADMIN_UPDATE,
					Permission.SUPER_ADMIN_DEELTE,
					Permission.ADMIN_CREATE,
					Permission.ADMIN_READ,
					Permission.ADMIN_UPDATE,
					Permission.ADMIN_DEELTE,
					Permission.EMPLOYEE_CREATE,
					Permission.EMPLOYEE_READ,
					Permission.EMPLOYEE_UPDATE,
					Permission.EMPLOYEE_DELETE,
					Permission.TRAINEE_CREATE,
					Permission.TRAINEE_READ,
					Permission.TRAINEE_UPDATE,
					Permission.TRAINEE_DELETE
					)
			
			
			
			),
	ADMIN(
			Set.of(
					
					Permission.ADMIN_CREATE,
					Permission.ADMIN_READ,
					Permission.ADMIN_UPDATE,
					Permission.ADMIN_DEELTE,
					Permission.EMPLOYEE_CREATE,
					Permission.EMPLOYEE_READ,
					Permission.EMPLOYEE_UPDATE,
					Permission.EMPLOYEE_DELETE,
					Permission.TRAINEE_CREATE,
					Permission.TRAINEE_READ,
					Permission.TRAINEE_UPDATE,
					Permission.TRAINEE_DELETE
					)
			
			
			
			),
	EMPLOYEE(
			Set.of(
					

					Permission.EMPLOYEE_CREATE,
					Permission.EMPLOYEE_READ,
					Permission.EMPLOYEE_UPDATE,
					Permission.EMPLOYEE_DELETE,
					Permission.TRAINEE_CREATE,
					Permission.TRAINEE_READ,
					Permission.TRAINEE_UPDATE,
					Permission.TRAINEE_DELETE
					)
			
			
			
			),
	TRAINEE(
			Set.of(
					
					
					
					Permission.TRAINEE_CREATE,
					Permission.TRAINEE_READ,
					Permission.TRAINEE_UPDATE,
					Permission.TRAINEE_DELETE
					)
			
			
			
			),
	;
	

	@Getter
	private final Set<Permission> permissions;
	
	public List<SimpleGrantedAuthority> getAuthorities(){
		var authorities = getPermissions()
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
				.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return authorities;
	}
	
	
}
