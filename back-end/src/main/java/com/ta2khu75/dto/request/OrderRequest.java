package com.ta2khu75.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.entity.OrderStatus;
import com.ta2khu75.group.CreateGroup;
import com.ta2khu75.group.UpdateGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(@NotBlank(groups = CreateGroup.class) @JsonProperty("account_email") String accountEmail, @NotNull(groups = UpdateGroup.class) @JsonProperty("order_status") OrderStatus orderStatus) {
}