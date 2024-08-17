package com.ta2khu75.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ta2khu75.repository.AccountRepository;
import com.ta2khu75.service.impl.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Value("${app.api-prefix}")
	private String apiPrefix;
	private final JwtService jwtService;
	private final AccountRepository accountRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(isByPassToken(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		final String token = authHeader.substring(7);
		final String userEmail = jwtService.extractUsername(token);
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetail = accountRepository.findByEmail(userEmail).orElse(null);
			if (jwtService.isTokenValid(token, userDetail)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetail,
						null, userDetail.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	 private boolean isByPassToken(@NotNull HttpServletRequest request) {
		 List<Map.Entry<String, String>> bypassTokens = List.of(
			        Map.entry(apiPrefix + "/auth", "POST"),
			        Map.entry(apiPrefix + "/user", "POST"),
			        Map.entry(apiPrefix + "/product", "GET")
			);
	        String requestPath = request.getServletPath();
	        String requestMethod = request.getMethod();
	        if(requestPath.startsWith("/"+apiPrefix+"/users/validator-email")
	                && requestMethod.equals("POST")) {
	            return true;
	        }
	        for (Map.Entry<String,String> bypassToken : bypassTokens) {
	            if (bypassToken.getKey().contains(requestPath) && bypassToken.getValue().contains(requestMethod)) {
	                return true;
	            }
	        }
	        return false;
	    }
}
