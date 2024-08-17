package com.ta2khu75.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ta2khu75.dto.request.OrderItemRequest;
import com.ta2khu75.dto.response.OrderItemResponse;
import com.ta2khu75.repository.OrderItemRepository;
import com.ta2khu75.service.OrderItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderItemServiceImpl implements OrderItemService{
	OrderItemRepository repo;
	@Override
	public OrderItemResponse create(OrderItemRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemResponse readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderItemResponse> readByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
