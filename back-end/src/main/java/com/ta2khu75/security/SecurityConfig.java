package com.ta2khu75.security;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SecurityConfig {
	private final String[] PUBLIC_POST_ENDPOINT = { "/account", "/auth", "/auth/google"};
	private final String[] PUBLIC_GET_ENDPOINT = {"/auth/**","/category/**","/brand", "/brand/image/*", "/product","/product/*", "/product/image/*" };
	@Value("${app.api-prefix}")
	String apiPrefix;
	@Value("${jwt.signature}")
	String jwtSignature;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(t -> t.disable())
				.cors(Customizer.withDefaults())
				.addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.authorizeHttpRequests(
						authorize -> authorize
								.requestMatchers(HttpMethod.POST,
										Arrays.stream(PUBLIC_POST_ENDPOINT).map(t -> apiPrefix + t)
												.toArray(String[]::new))
								.permitAll()
								.requestMatchers("/").permitAll()
								.requestMatchers(HttpMethod.GET, Arrays.stream(PUBLIC_GET_ENDPOINT).map(t -> apiPrefix
										+ t)
												.toArray(String[]::new))
								.permitAll().anyRequest().authenticated());
		return http.build();
	}

	@Bean
	CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:5173");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
