package com.in2it.jwtauth.security.auth;

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
	private String username;

}
