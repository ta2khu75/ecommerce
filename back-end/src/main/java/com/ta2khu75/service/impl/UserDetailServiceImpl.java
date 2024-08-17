package com.ta2khu75.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ta2khu75.entity.Account;
import com.ta2khu75.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
	private final AccountRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email : "+username));
	}
//	private UserDetails toUserDetails(Account account) {
//		
//	}

}
