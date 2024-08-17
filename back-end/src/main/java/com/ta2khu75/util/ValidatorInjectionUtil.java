package com.ta2khu75.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidatorInjectionUtil {
	private final Validator validator;

	public <T> void validation(T object) {
		BindingResult bindingResult = new BeanPropertyBindingResult(object, object.getClass().getSimpleName());
		validator.validate(object, bindingResult);
		if (bindingResult.hasErrors()) {
			// Handle errors as needed
			StringBuilder sb = new StringBuilder();
			for (ObjectError error : bindingResult.getAllErrors()) {
				sb.append(error.getDefaultMessage()).append("\n");
			}
			throw new IllegalArgumentException("Validation failed for category request: " + sb.toString());
		}
	}
}
