package com.ta2khu75.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.dto.request.AccountRequest;
import com.ta2khu75.dto.request.update.AccountInfoUpd;
import com.ta2khu75.dto.request.update.AccountPasswordUpd;
import com.ta2khu75.dto.request.update.AccountRoleUpd;
import com.ta2khu75.dto.response.AccountResponse;
import com.ta2khu75.entity.Account;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "socialAccount", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "role", ignore = true)
	Account toMdl(AccountRequest accountReq);
	AccountResponse toRes(Account account);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "socialAccount", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	void updateInfo(AccountInfoUpd req, @MappingTarget Account account);
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "socialAccount", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "firstName", ignore = true)
	@Mapping(target = "lastName", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "role", ignore = true)
	void updatePassword(AccountPasswordUpd req, @MappingTarget Account account);
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "socialAccount", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "firstName", ignore = true)
	@Mapping(target = "lastName", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "password", ignore = true)
	void updateRole(AccountRoleUpd req, @MappingTarget Account account);
}
