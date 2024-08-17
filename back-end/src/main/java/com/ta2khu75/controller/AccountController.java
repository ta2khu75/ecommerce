package com.ta2khu75.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ta2khu75.dto.ApiResponse;
import com.ta2khu75.dto.PageResponse;
import com.ta2khu75.dto.request.AccountRequest;
import com.ta2khu75.dto.request.update.AccountInfoUpd;
import com.ta2khu75.dto.request.update.AccountPasswordUpd;
import com.ta2khu75.dto.request.update.AccountRoleUpd;
import com.ta2khu75.dto.response.AccountResponse;
import com.ta2khu75.entity.SocialAccount;
import com.ta2khu75.service.AccountService;
import com.ta2khu75.util.ObjectUtil;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/account")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
	AccountService svc;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<AccountResponse> create(@Valid @RequestPart("account") String accountString,
			@Valid @RequestPart(name = "social_account", required = false) String socialAccountString)
			throws JsonProcessingException {
		AccountRequest account = ObjectUtil.convertToObject(accountString, AccountRequest.class);

		SocialAccount socialAccount = socialAccountString != null
				? ObjectUtil.convertToObject(socialAccountString, SocialAccount.class)
				: null;
		return ApiResponse.<AccountResponse>builder().data(svc.create(account, socialAccount)).build();
	}

	@GetMapping
	public ApiResponse<PageResponse<AccountResponse>> readPage(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) Boolean enabled, @RequestParam(required = false) Boolean nonLocked,
			@RequestParam(required = false) String roleString, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "8") int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return ApiResponse.<PageResponse<AccountResponse>>builder()
				.data(svc.readPage(keyword, firstName, lastName, enabled, nonLocked, roleString, pageable)).build();
	}

	@PutMapping("password/{email}")
	public ApiResponse<AccountResponse> updatePassword(@PathVariable String email,
			@Valid @RequestBody AccountPasswordUpd req) {
		return ApiResponse.<AccountResponse>builder().data(svc.updatePassword(email, req)).build();
	}

	@PutMapping("info/{email}")
	public ApiResponse<AccountResponse> updateInfo(@PathVariable String email, @Valid @RequestBody AccountInfoUpd req) {
		return ApiResponse.<AccountResponse>builder().data(svc.updateInfo(email, req)).build();
	}

	@PutMapping("role/{email}")
	public ApiResponse<AccountResponse> updateRole(@PathVariable String email, @RequestBody AccountRoleUpd req) {
		return ApiResponse.<AccountResponse>builder().data(svc.updateRole(email, req)).build();
	}
	@GetMapping("my-info")
	public ApiResponse<AccountResponse> readMyInfo() {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return ApiResponse.<AccountResponse>builder().data(svc.readByEmail(email)).build();
    }
}
