package com.efekansalman.Library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize
				// public endpoints
				.requestMatchers("/api/users/register", "/api/books/search/**").permitAll()
				// Admin only endpoints
				.requestMatchers("/api/reports/**", "/api/books").hasRole("ADMIN")
				// Customer only endpoints
				.requestMatchers("/api/lendings/**", "/api/reservations/**", "/api/donations/**").hasRole("CUSTOMER")
				// All other requests require authentication
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
					.loginProcessingUrl("/api/login") 
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/api/logout")
					.permitAll()
			)
			.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
