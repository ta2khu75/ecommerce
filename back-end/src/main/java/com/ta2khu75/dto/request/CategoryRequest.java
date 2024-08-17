package com.ta2khu75.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoryRequest(@NotBlank String name, int level, Integer parent) {
}