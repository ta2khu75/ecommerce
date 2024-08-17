package com.ta2khu75.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountRequest(@Email @NotBlank String email, @NotBlank String password,
		@JsonProperty("confirm_password") @NotBlank String comfirmPassword,
		@JsonProperty("first_name") @NotBlank String firstName, @JsonProperty("last_name") @NotBlank String lastName) {
}