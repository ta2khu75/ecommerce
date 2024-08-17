package com.ta2khu75.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record AccountInfoUpd(@JsonProperty("first_name") @NotBlank String firstName,@JsonProperty("last_name") @NotBlank String lastName, @NotBlank String address) {
	
}
