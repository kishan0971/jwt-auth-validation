package com.in2it.jwtauth.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.in2it.jwtauth.exception.JwtTokenException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RequiredArgsConstructor
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//	
//
//	private final JwtService jwtService;
//	private final UserDetailsService userDetailsService;
//	
//
//	@Override
//	protected void doFilterInternal(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response,@NotNull FilterChain filterChain)
//			throws ServletException, IOException {
//		final String jwt;
//		final String username;
//		// verify whether request has authorization header and it has bearer in it
//		final String authHeader = request.getHeader("Authorization");
//		if(authHeader == null || !authHeader.startsWith("Bearer ")) {  //if token is not present or wrong
//			filterChain.doFilter(request, response);
//			return;
//		}
//		
//		// If token is present
//		// Extract user from the token
//		jwt = authHeader.substring(7);
//		//verify whether user is present in db
//		// Verify whether token is valid
//		
//		// Get username from token
//		username = jwtService.extractUsername(jwt);
//		
//		// If user is present and no authentication object in sicurityContext
//		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) { // if it is all ready validated then it dose not need to execute the filter
//			// If valid set to security context holder
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//			
//			// If valid set to security context holder
//			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//		}
//		
//		filterChain.doFilter(request, response);
//		
//		
//	
//	}
//	
//	/*
//	 * By pass all whitelisted request
//	 */
//	// verify if it is whitelisted path and if yes dont't do anything
//	@Override
//	protected boolean shouldNotFilter(@NotNull HttpServletRequest request) throws ServletException {
//		
////		return super.shouldNotFilter(request);
//		return request.getServletPath().contains("/in2it/v1/auth/**");
//	}
//	
//
//
//}
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String jwt = authHeader.substring(7);
//        String username;
//
//        try {
//            username = jwtService.extractUsername(jwt);
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//        } catch (Exception e) {
//            // Log the exception if needed
//            // For example: log.error("Failed to process JWT token", e);
//        	log.error("Failed to process JWT token", e);
//        }
//
//        filterChain.doFilter(request, response);
//    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Proceed without authentication if header is missing or invalid
            return;
        }

        final String jwt = authHeader.substring(7);
        String username;

        try {
            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (JwtTokenException e) {
            // Handle specific JWT token exceptions
            log.error("JWT token exception: ", e);
        } catch (UsernameNotFoundException e) {
            // Handle cases where the username is not found
            log.error("Username not found: ", e);
        } catch (Exception e) {
            // Handle any other exceptions
            log.error("Failed to process JWT token", e);
        }

        filterChain.doFilter(request, response); // Proceed to the next filter or endpoint
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/in2it/v1/auth") && request.getMethod().equalsIgnoreCase("POST"); // Adjust path matching as needed
    }
}