package com.in2it.jwtauthenticationandauthorization.config;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.in2it.jwtauthenticationandauthorization.auth.AuthenticationRequest;
import com.in2it.jwtauthenticationandauthorization.auth.AuthenticationResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "865386532653286532865323fcbsnnh863bccferfywertbcrt37r34";

	public String generateToken(UserDetails user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("authorities", populateAuthorities(user.getAuthorities())) // seting properties
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+86400000))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	
	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	//To get athority
	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : authorities) {
			authoritiesSet.add(authority.getAuthority());
			
		}
		return String.join(",", authoritiesSet);
	}
	
	
	// For extarcting all the  claims from token
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	// Extract username from token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	// Extracting username from all the claims
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()));
	}
	
	
}
