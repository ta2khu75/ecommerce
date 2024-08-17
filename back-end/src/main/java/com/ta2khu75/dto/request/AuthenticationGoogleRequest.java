package com.ta2khu75.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationGoogleRequest (@NotBlank String credential, @JsonProperty("client_id") String clientId){
}
