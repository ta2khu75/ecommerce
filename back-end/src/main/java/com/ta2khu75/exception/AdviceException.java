package com.ta2khu75.exception;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ta2khu75.dto.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AdviceException implements ResponseBodyAdvice<Object> {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> notFound(NotFoundException ex) {
		return ResponseEntity.badRequest()
				.body(ApiResponse.<Void>builder().message(ex.getMessage()).success(false).build());
	}

	@ExceptionHandler(NotMatchException.class)
	public ResponseEntity<ApiResponse<Void>> notMatch(NotMatchException ex) {
		return ResponseEntity.badRequest()
				.body(ApiResponse.<Void>builder().message(ex.getMessage()).success(false).build());
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse<Void>> io(IOException ex) {
		return ResponseEntity.badRequest()
				.body(ApiResponse.<Void>builder().message(ex.getMessage()).success(false).build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> exception(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.internalServerError()
				.body(ApiResponse.<Void>builder().message(ex.getMessage()).success(false).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> notValid(MethodArgumentNotValidException ex) {
		String errors = ex.getBindingResult().getAllErrors().stream().map(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			return fieldName + ": " + errorMessage;
		}).collect(Collectors.joining("; "));
		return ResponseEntity.badRequest()
				.body(ApiResponse.<Map<String, String>>builder().message(errors).success(false).build());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Void>> accessDenied(AccessDeniedException ex) {
		return ResponseEntity.status(403)
				.body(ApiResponse.<Void>builder().message(ex.getMessage()).success(false).build());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse<Void>> illegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.badRequest()
				.body(ApiResponse.<Void>builder().message(ex.getMessage()).success(false).build());
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		int statusCode = servletResponse.getStatus();
		if(statusCode<400) {
			
		}else {
			
		}
		// TODO Auto-generated method stub
		return body;
	}

}
