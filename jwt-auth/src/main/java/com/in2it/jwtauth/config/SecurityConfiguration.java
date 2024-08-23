package com.in2it.jwtauth.config;

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

import com.in2it.jwtauth.entity.Permission;
import com.in2it.jwtauth.entity.Role;
import com.in2it.jwtauth.exception.JwtAccessDeniedHandler;
import com.in2it.jwtauth.exception.JwtAuthEntryPoint;

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
			"/api/test/**", "/authenticate" ,"register"};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(req ->

		req.requestMatchers("/in2it/v1/auth/**").permitAll()
				.requestMatchers(WHITE_LIST_URL).permitAll()
				.requestMatchers("in2it/v1/auth/register").permitAll()
				.requestMatchers("in2it/employees/roles").hasRole("SUPER_ADMIN")
				.requestMatchers("in2it/employees/roles/permission").hasRole("SUPER_ADMIN")
				.requestMatchers(HttpMethod.GET, "in2it/employees/roles/permission/**").hasAnyAuthority("read", "update")
				.anyRequest().authenticated()
				)
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
		.exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler))

				.build();
	}

	
}



//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
//@AllArgsConstructor
//public class SecurityConfiguration {
//
//    private final AuthenticationProvider authenticationProvider;
//    private final JwtAuthFilter jwtAuthFilter;
//    private final JwtAuthEntryPoint jwtAuthEntryPoint;
//    private final JwtAccessDeniedHandler accessDeniedHandler;
//
//    private static final String[] WHITE_LIST_URL = {
//        "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
//        "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**",
//        "/configuration/ui", "/configuration/security", "/swagger-ui/**",
//        "/webjars/**", "/swagger-ui.html", "/api/auth/**", "/api/test/**", "/authenticate"
//    };
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//            .csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                    .requestMatchers(WHITE_LIST_URL).permitAll()
//                    .anyRequest().authenticated()  // Change to authenticated for security
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authenticationProvider(authenticationProvider)
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//            .exceptionHandling(exceptionHandling ->
//                exceptionHandling
//                    .authenticationEntryPoint(jwtAuthEntryPoint)
//                    .accessDeniedHandler(accessDeniedHandler)
//            )
//            .build();
//    }
//}