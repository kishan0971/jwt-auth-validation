package com.in2it.jwtauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.in2it.jwtauth.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//	
//	
//	private final EmployeeRepository repository;
//
//	/*
//	 * been for password encoder
//	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	
//	/*
//	 * userdetails service
//	 */
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return username -> repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//	}
//	
//	
//	
//	/*
//	 * bean for authentication provider
//	 */
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}
//	
//	/*
//	 * been for authentication manager
//	 */
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
//		return config.getAuthenticationManager();
//		
//	}
//
//}


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final EmployeeRepository repository;

    /*
     * Bean for password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Bean for user details service
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /*
     * Bean for authentication provider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /*
     * Bean for authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
