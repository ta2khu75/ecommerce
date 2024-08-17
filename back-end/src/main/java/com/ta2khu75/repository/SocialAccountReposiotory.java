package com.ta2khu75.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.entity.SocialAccount;

public interface SocialAccountReposiotory extends JpaRepository<SocialAccount, Long>{
	SocialAccount findByEmailAndProvider(String email, String provider);
}
