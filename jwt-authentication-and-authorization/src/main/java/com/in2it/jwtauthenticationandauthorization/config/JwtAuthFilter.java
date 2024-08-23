package com.in2it.jwtauthenticationandauthorization.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	

	@Override
	protected void doFilterInternal(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response,@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		final String jwt;
		final String email;
		// verify whether request has authorization header and it has bearer in it
		final String authHeader = request.getHeader("Authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {  //if token is not present or wrong
			filterChain.doFilter(request, response);
			return;
		}
		
		// If token is present
		// Extract user from the token
		jwt = authHeader.substring(7);
		//verify whether user is present in db
		// Verify whether token is valid
		
		// Get username from token
		email = jwtService.extractUsername(jwt);
		
		// If user is present and no authentication object in sicurityContext
		if(email != null && SecurityContextHolder.getContext().getAuthentication()==null) { // if it is all ready validated then it dose not need to execute the filter
			// If valid set to security context holder
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
			
			// If valid set to security context holder
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		
		filterChain.doFilter(request, response);
		
		
	
	}
	
	/*
	 * By pass all whitelisted request
	 */
	// verify if it is whitelisted path and if yes dont't do anything
	@Override
	protected boolean shouldNotFilter(@NotNull HttpServletRequest request) throws ServletException {
		
//		return super.shouldNotFilter(request);
		return request.getServletPath().contains("/in2it/v1/auth/*");
	}
	


}
