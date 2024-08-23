package com.in2it.jwtauthenticationandauthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.in2it.jwtauthenticationandauthorization.exception.JwtAccessDeniedHandler;
import com.in2it.jwtauthenticationandauthorization.exception.JwtAuthEntryPoint;
import com.in2it.jwtauthenticationandauthorization.model.Permission;
import com.in2it.jwtauthenticationandauthorization.model.Role;

import lombok.AllArgsConstructor;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfiguration {
	
	
	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthFilter jwtAuthFilter;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	private final JwtAccessDeniedHandler accessDeniedHandler;

	private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
			"/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
			"/api/test/**", "/authenticate" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(req ->

		req.requestMatchers("/in2it/v1/auth/*").permitAll()
				.requestMatchers(WHITE_LIST_URL).permitAll()
				.requestMatchers("/in2it/v1/admin/**").hasAnyRole(Role.SUPER_ADMIN.name(), Role.ADMIN.name())
				.requestMatchers(HttpMethod.GET, "/in2it/v1/admin/**").hasAnyAuthority(Permission.SUPER_ADMIN_READ.name(), Permission.ADMIN_READ.name())
				.requestMatchers(HttpMethod.POST, "/in2it/v1/admin/**").hasAnyAuthority(Permission.SUPER_ADMIN_CREATE.name(), Permission.ADMIN_CREATE.name())
				.anyRequest().authenticated())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
		.exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler))

				.build();
	}

	
}
