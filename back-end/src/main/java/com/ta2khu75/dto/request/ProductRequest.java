package com.ta2khu75.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(@NotBlank String name,
		@JsonProperty("brand_id") @NotNull Integer brandId, String description,
		@NotBlank String category) {
}