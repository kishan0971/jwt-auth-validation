package com.in2it.jwtauthenticationandauthorization.auth;

import com.in2it.jwtauthenticationandauthorization.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResisterRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;

}
