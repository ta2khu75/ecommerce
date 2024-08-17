package com.ta2khu75.dto.response;

public record AuthenticationResponse(AccountResponse account, String token, boolean authentication) {}