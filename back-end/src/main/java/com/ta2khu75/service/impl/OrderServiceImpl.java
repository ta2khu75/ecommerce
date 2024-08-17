package com.ta2khu75.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta2khu75.dto.request.OrderRequest;
import com.ta2khu75.dto.response.OrderResponse;
import com.ta2khu75.entity.Account;
import com.ta2khu75.entity.Order;
import com.ta2khu75.exception.NotFoundException;
import com.ta2khu75.mapper.OrderMapper;
import com.ta2khu75.repository.AccountRepository;
import com.ta2khu75.repository.OrderRepository;
import com.ta2khu75.service.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {
	AccountRepository accountRepo;
	OrderRepository repo;
	OrderMapper mppr;
	@Override
	public OrderResponse create(OrderRequest req) {
		Account account=accountRepo.findByEmail(req.accountEmail()).orElseThrow(()->new NotFoundException("Could not find account with email: "+req.accountEmail()));
		Order order=new Order();
		order.setAccount(account);
		return mppr.toRes(repo.save(order));
	}

	@Override
	public OrderResponse readById(Long id) {
		return mppr.toRes(repo.findById(id).orElseThrow(()->new NotFoundException("Could not find order with id: "+id)));
	}

	@Override
	public Page<OrderResponse> readByAccountEmail(String accountEmail, Pageable pageable) {
		return repo.findByAccountEmail(accountEmail, pageable).map(mppr::toRes);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public OrderResponse update(Long id, OrderRequest req) {
		Order order=repo.findById(id).orElseThrow(()->new NotFoundException("Could not find order with id: "+id));
		order.setOrderStatus(req.orderStatus());
		return mppr.toRes(repo.save(order));
	}

}
