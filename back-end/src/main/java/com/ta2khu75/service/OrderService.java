package com.ta2khu75.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ta2khu75.dto.request.OrderRequest;
import com.ta2khu75.dto.response.OrderResponse;

public interface OrderService {
	OrderResponse create(OrderRequest req);
	OrderResponse readById(Long id);
	OrderResponse update(Long id, OrderRequest req);
	Page<OrderResponse> readByAccountEmail(String accountEmail, Pageable pageable);
	void delete(Long id);
}
