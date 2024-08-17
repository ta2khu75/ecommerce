package com.ta2khu75.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.dto.response.OrderResponse;
import com.ta2khu75.entity.Order;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	@Mapping(target = "create_date", ignore = true)
	OrderResponse toRes(Order order);
}
