package com.ta2khu75.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ta2khu75.dto.PageResponse;
import com.ta2khu75.dto.request.AccountRequest;
import com.ta2khu75.dto.request.update.AccountInfoUpd;
import com.ta2khu75.dto.request.update.AccountPasswordUpd;
import com.ta2khu75.dto.request.update.AccountRoleUpd;
import com.ta2khu75.dto.response.AccountResponse;
import com.ta2khu75.entity.Account;
import com.ta2khu75.entity.Role;
import com.ta2khu75.entity.SocialAccount;
import com.ta2khu75.exception.ExistingException;
import com.ta2khu75.exception.NotFoundException;
import com.ta2khu75.exception.NotMatchException;
import com.ta2khu75.mapper.AccountMapper;
import com.ta2khu75.repository.AccountRepository;
import com.ta2khu75.service.AccountService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
	AccountRepository repo;
	AccountMapper mppr;
	PasswordEncoder passwordEncoder;

	@Override
	public AccountResponse create(AccountRequest req, SocialAccount socialAccount) {
		if (req.comfirmPassword().equals(req.password())) {
			if (repo.findByEmail(req.email()).orElse(null) == null) {
				Account account = mppr.toMdl(req);
				account.setRole(Role.USER);
				account.setPassword(passwordEncoder.encode(account.getPassword()));
				account.setSocialAccount(socialAccount);
				if(socialAccount!=null) account.setEnabled(true);
				return mppr.toRes(repo.save(account));
			} else {
				throw new ExistingException("Email already exists");
			}
		}
		throw new NotMatchException("Password and confirm password do not match");
	}

	@Override
	public List<AccountResponse> readAll() {
		return repo.findAll().stream().map(mppr::toRes).collect(Collectors.toList());
	}

	@Override
	public AccountResponse readByEmail(String email) {
		return mppr.toRes(repo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email)));
	}

	@Override
	public void deleteByEmail(String email) {
		repo.deleteByEmail(email);
	}

	@Override
	public AccountResponse updateInfo(String email, AccountInfoUpd req) {
		Account accountExisting = repo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account email: " + email));
		mppr.updateInfo(req, accountExisting);
		return mppr.toRes(repo.save(accountExisting));
	}

	@Override
	public AccountResponse updatePassword(String email, AccountPasswordUpd req) {
		if (req.password().equals(req.confimPassword())) {
			Account accountExisting = repo.findByEmail(email)
					.orElseThrow(() -> new NotFoundException("Could not find account email: " + email));
			mppr.updatePassword(req, accountExisting);
			accountExisting.setPassword(passwordEncoder.encode(req.password()));
			return mppr.toRes(repo.save(accountExisting));
		}
		throw new NotMatchException("Password and confirm password do not match");
	}

	@Override
	public AccountResponse updateRole(String email, AccountRoleUpd req) {
		Account accountExisting = repo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account email: " + email));
		mppr.updateRole(req, accountExisting);
		return mppr.toRes(repo.save(accountExisting));
	}

	@Override
	public PageResponse<AccountResponse> readPage(String keyword, String firstName, String lastName, Boolean enabled,
			Boolean nonLocked, String roleString, Pageable pageable) {
		Role role = null;
		if (roleString != null && !roleString.isBlank()) {
			role = Role.valueOf(roleString);
		}
		Page<AccountResponse> page = repo.readPage(keyword, firstName, lastName, enabled, nonLocked, role, pageable)
				.map(mppr::toRes);
		;
		return new PageResponse<>(pageable.getPageNumber() + 1, page.getTotalPages(), page.getTotalElements(),
				page.getContent());

	}

}
