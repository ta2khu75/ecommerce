package com.ta2khu75.service;

import java.util.List;

import com.ta2khu75.dto.request.OrderItemRequest;
import com.ta2khu75.dto.response.OrderItemResponse;

public interface OrderItemService {
	OrderItemResponse create(OrderItemRequest req);
	OrderItemResponse readById(Long id);
	List<OrderItemResponse> readByOrderId(Long orderId); 
	void delete(Long id);
}
