package com.study.DoctorAppointmentSystem.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.study.DoctorAppointmentSystem.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request

						// Public APIs
						.requestMatchers(HttpMethod.POST, "/users/register", "/auth/login").permitAll()

						.requestMatchers(HttpMethod.GET, "/doctors", "/doctors/{id}", "/doctors/get-image/{id}")
						.permitAll()

						// Patient APIs
						.requestMatchers("/patients/**").hasRole("PATIENT")

						.requestMatchers(HttpMethod.POST, "/appointments").hasRole("PATIENT")

						.requestMatchers(HttpMethod.PUT, "/appointments/{id}").hasRole("PATIENT")

						.requestMatchers(HttpMethod.GET, "/appointments/patient/**").hasRole("PATIENT")

						.requestMatchers(HttpMethod.PUT, "/appointments/cancel/**").hasRole("PATIENT")

						// Doctor APIs
						.requestMatchers("/doctors/**").hasRole("DOCTOR")

						.requestMatchers(HttpMethod.GET, "/appointments/doctor/**").hasRole("DOCTOR")

						.requestMatchers(HttpMethod.PUT, "/appointments/accept/**").hasRole("DOCTOR")

						.requestMatchers(HttpMethod.PUT, "/appointments/reject/**").hasRole("DOCTOR")

						.requestMatchers(HttpMethod.PUT, "/appointments/complete/**").hasRole("DOCTOR")

						// Admin APIs
						.requestMatchers("/users/**").hasRole("ADMIN")

						.requestMatchers(HttpMethod.GET, "/appointments").hasRole("ADMIN")

						.anyRequest().authenticated());
		;

		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

		httpSecurity
				.exceptionHandling(authentication -> authentication.authenticationEntryPoint(authenticationEntryPoint));

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}

//	global cors
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
