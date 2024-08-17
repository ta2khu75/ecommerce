package com.ta2khu75.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import com.ta2khu75.dto.ApiResponse;
import com.ta2khu75.dto.request.AuthenticationRequest;
import com.ta2khu75.dto.request.AuthenticationGoogleRequest;
import com.ta2khu75.dto.response.AuthenticationResponse;
import com.ta2khu75.dto.response.SocialAccountResponse;
import com.ta2khu75.entity.Account;
import com.ta2khu75.entity.SocialAccount;
import com.ta2khu75.repository.AccountRepository;
import com.ta2khu75.repository.SocialAccountReposiotory;
import com.ta2khu75.service.AuthenticationService;
import com.ta2khu75.util.ObjectUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
	AuthenticationService svc;
	SocialAccountReposiotory socialAccountReposiotory;
	AccountRepository repository;
	
	@PostMapping
	public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest req) throws JOSEException {
        return ApiResponse.<AuthenticationResponse>builder().data(svc.login(req)).build();
    }
	@PostMapping("google")
	public ApiResponse<SocialAccountResponse> loginGoogle(@RequestPart("request") String req) throws JOSEException, JsonProcessingException, ParseException {
		AuthenticationGoogleRequest googleAuthRequest = ObjectUtil.convertToObject(req, AuthenticationGoogleRequest.class);
		SignedJWT jwt=SignedJWT.parse(googleAuthRequest.credential());
		JWTClaimsSet jwtClaimsSet =jwt.getJWTClaimsSet();
		String email=(String) jwtClaimsSet.getClaim("email");
		Account account =repository.findBySocialAccountEmailAndSocialAccountProvider(email, "google");
		SocialAccount socialAccount=null;
		if(account==null) {
			socialAccount=new SocialAccount();
            socialAccount.setEmail(email);
            socialAccount.setProvider("google");
            socialAccountReposiotory.save(socialAccount);
		}
		return ApiResponse.<SocialAccountResponse>builder().data(new SocialAccountResponse(socialAccount, svc.loginSocial(account))).build();
	}
}
