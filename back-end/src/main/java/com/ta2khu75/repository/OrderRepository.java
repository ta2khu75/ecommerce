package com.ta2khu75.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findByAccountEmail(String accountEmail, Pageable pageable);
}
