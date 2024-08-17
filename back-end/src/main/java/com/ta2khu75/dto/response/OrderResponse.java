package com.ta2khu75.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderResponse(Long id, @JsonProperty("create_date") LocalDateTime createdDate, @JsonProperty("create_date") LocalDateTime create_date) {
}