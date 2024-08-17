package com.ta2khu75.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.ta2khu75.dto.request.AuthenticationRequest;
import com.ta2khu75.dto.response.AccountResponse;
import com.ta2khu75.dto.response.AuthenticationResponse;
import com.ta2khu75.entity.Account;
import com.ta2khu75.exception.UnAuthenticatedException;
import com.ta2khu75.mapper.AccountMapper;
import com.ta2khu75.repository.AccountRepository;
import com.ta2khu75.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {
	final AccountRepository accountRepo;
	final AccountMapper accountMppr;
	final JwtService jwtService;
	final AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse login(AuthenticationRequest req) throws JOSEException {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.password()));
		Account account = accountRepo.findByEmail(req.email())
				.orElseThrow(() -> new UnAuthenticatedException("Email or passowrd incorrect"));
		return new AuthenticationResponse(accountMppr.toRes(account), jwtService.createToken(null, account), true);
	}

	@Override
	public AuthenticationResponse loginSocial(Account account) throws JOSEException {
		if (account == null) {
			return null;
		} else {
			AccountResponse accountResponse = accountMppr.toRes(account);
			String token = jwtService.createToken(null, account);
			return new AuthenticationResponse(accountResponse, token, true);
		}
	}

}
