package com.ta2khu75.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
