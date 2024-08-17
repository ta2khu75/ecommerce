package com.ta2khu75.service;

import com.nimbusds.jose.JOSEException;
import com.ta2khu75.dto.request.AuthenticationRequest;
import com.ta2khu75.dto.response.AuthenticationResponse;
import com.ta2khu75.entity.Account;

public interface AuthenticationService {
	AuthenticationResponse login(AuthenticationRequest req) throws JOSEException;
	AuthenticationResponse loginSocial(Account account) throws JOSEException;
}
