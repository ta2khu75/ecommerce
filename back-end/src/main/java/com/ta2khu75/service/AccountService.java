package com.ta2khu75.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ta2khu75.dto.PageResponse;
import com.ta2khu75.dto.request.AccountRequest;
import com.ta2khu75.dto.request.update.AccountInfoUpd;
import com.ta2khu75.dto.request.update.AccountPasswordUpd;
import com.ta2khu75.dto.request.update.AccountRoleUpd;
import com.ta2khu75.dto.response.AccountResponse;
import com.ta2khu75.entity.SocialAccount;

public interface AccountService {
	AccountResponse create(AccountRequest accountRequest,SocialAccount socialAccountRequest);
	List<AccountResponse> readAll();
	AccountResponse readByEmail(String email);
	void deleteByEmail(String email);
	AccountResponse updateInfo(String email, AccountInfoUpd req);
	AccountResponse updatePassword(String email, AccountPasswordUpd req);
	AccountResponse updateRole(String email, AccountRoleUpd req);
	@PreAuthorize("hasAuthority('ADMIN')")
	PageResponse<AccountResponse> readPage(String keyword, String firstName, String lastName, Boolean enabled, Boolean nonLocked, String roleString, Pageable pageable);
}
