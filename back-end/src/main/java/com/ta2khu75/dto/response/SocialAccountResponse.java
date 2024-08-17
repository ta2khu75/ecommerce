package com.ta2khu75.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.entity.SocialAccount;

public record SocialAccountResponse(@JsonProperty("social_account") SocialAccount socialAccount, AuthenticationResponse account) {
}