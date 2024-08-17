package com.ta2khu75.service.impl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ta2khu75.entity.Account;

@Service
public class JwtService {

	@Value("${jwt.signature}")
	private String jwtSignature;

	// Extracts the username from the token
	public String extractUsername(String token) {
		try {
			return extractClaim(token, JWTClaimsSet::getSubject);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private SignedJWT getSignedJWT(String token) throws ParseException {
		return SignedJWT.parse(token);
	}

	// Retrieves the claims set from the token
	private JWTClaimsSet getClaimsSet(String token) throws ParseException {
		return getSignedJWT(token).getJWTClaimsSet();
	}

	// Extracts a specific claim from the token using a claims resolver function
	private <T> T extractClaim(String token, Function<JWTClaimsSet, T> claimsResolver) throws ParseException {
		final JWTClaimsSet claimsSet = getClaimsSet(token);
		return claimsResolver.apply(claimsSet);
	}

	// Verifies the token using the provided JWT signature
	public boolean verifierToken(String token) {
		try {
			JWSVerifier verifier = new MACVerifier(jwtSignature.getBytes());
			return getSignedJWT(token).verify(verifier);
		} catch (ParseException | JOSEException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Checks if the token is valid by verifying the token, checking expiration and
	// ensuring userDetails is not null
	public boolean isTokenValid(String token, UserDetails userDetails) {
		return verifierToken(token) && !isTokenExpired(token) && userDetails != null;
	}

	// Checks if the token has expired
	private boolean isTokenExpired(String token) {
		try {
			return extractClaim(token, JWTClaimsSet::getExpirationTime).before(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Creates a new token with optional extra claims
	public String createToken(Map<String, Object> extraClaims, Account account) throws JOSEException {
		JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder().subject(account.getEmail())
				.issuer("com.ta2khu75").issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
				.jwtID(UUID.randomUUID().toString()).claim("scope", account.getRole());
		if (extraClaims != null) {
			extraClaims.forEach(claimsBuilder::claim);
		}

		JWTClaimsSet jwtClaimsSet = claimsBuilder.build();
		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
		JWSObject jwsObject = new JWSObject(jwsHeader, payload);
		jwsObject.sign(new MACSigner(jwtSignature.getBytes()));
		return jwsObject.serialize();
	}
}