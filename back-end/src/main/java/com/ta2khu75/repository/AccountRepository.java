package com.ta2khu75.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.entity.Account;
import com.ta2khu75.entity.Role;
import com.ta2khu75.entity.SocialAccount;

public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query("SELECT a FROM Account a where (:keyword IS NULL OR a.email like %:keyword% OR a.firstName like %:keyword% OR a.lastName like %:keyword%)"
			+ "AND (:firstName IS NULL OR a.firstName like %:firstName%)"
			+ "AND (:lastName IS NULL OR a.lastName like %:lastName%)"
			+ "AND (:role IS NULL OR a.role = :role)"
			+ "AND (:enabled IS NULL OR a.enabled = :enabled)"
			+ "AND (:nonLocked IS NULL OR a.nonLocked = :nonLocked)")
	Page<Account> readPage(@Param("keyword") String keyword, @Param("firstName") String firstName, @Param("lastName") String lastName,
            @Param("enabled") Boolean enabled, @Param("nonLocked") Boolean nonLocked, @Param("role") Role role, Pageable pageable);
	Optional<Account> findByEmail(String email);
	Account findBySocialAccountEmailAndSocialAccountProvider(String email, String provider);
	Account findBySocialAccount(SocialAccount socialAccount);
	void deleteByEmail(String email);
}
