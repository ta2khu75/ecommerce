package com.ta2khu75.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(@NotBlank String email ,@NotBlank String password) {
}