package com.in2it.jwtauthenticationandauthorization.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.in2it.jwtauthenticationandauthorization.config.JwtService;
import com.in2it.jwtauthenticationandauthorization.model.User;
import com.in2it.jwtauthenticationandauthorization.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository repository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(ResisterRequest registerRequest) {
		
		 var user = User.builder()
				 .firstName(registerRequest.getFirstName())
				 .lastName(registerRequest.getLastName())
				 .email(registerRequest.getEmail())
				 .password(passwordEncoder.encode(registerRequest.getPassword()))
				 .role(registerRequest.getRole())
				 .build();
		 var savedUser = repository.save(user); 
		 String jwtTocken = jwtService.generateToken(savedUser);
		 return AuthenticationResponse.builder().accessToken(jwtTocken).build();
	
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request){
		// First Step(Create been for all)
			// We need to validate our request(validate whether password & username is correct)
			// Verify whether user present in data base
			// Which authentication provider -> DaoAuthenticationProvider(inject)
			// We need to authenticate using authentication manager injecting this authentication provider
		// Second Step
			//Verify whether userName and password is correct or not => UserNamePasswordAuthenticationToken
			//verify whether user present in db
			// Generate Token
			// Return the token
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (Exception e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		var user = repository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User Dosen't Exist"));
		
		String jwtTocken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().accessToken(jwtTocken).build();
	}

}
