package com.ta2khu75.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record AccountPasswordUpd(@NotBlank String password, @NotBlank @JsonProperty("confirm_password") String confimPassword) {

}
