package com.ta2khu75.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(@NotNull @Min(0) Long orderId,@NotNull @Min(0) Long productId, @Min(1) @NotNull Integer quantity) {

}
