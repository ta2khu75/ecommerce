package com.ta2khu75.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public record ProductImageRequest(@JsonProperty("product_id") @NotNull Long productId) {
}
