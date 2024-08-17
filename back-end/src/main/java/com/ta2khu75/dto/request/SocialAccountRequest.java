package com.ta2khu75.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SocialAccountRequest(@NotNull Long id, @NotBlank String provider, @NotBlank String email) {
}