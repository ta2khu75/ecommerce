package com.ta2khu75.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.entity.Role;

public record AccountResponse(String email, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,boolean enabled,
boolean nonLocked, String address, Role role) {
}
