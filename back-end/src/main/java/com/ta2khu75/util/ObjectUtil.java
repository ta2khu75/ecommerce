package com.ta2khu75.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private ObjectUtil() {
		throw new IllegalStateException("Utility class");
	}
	public static <T> T convertToObject(String jsonString, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.readValue(jsonString, clazz);
	}
}
