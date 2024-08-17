package com.ta2khu75.entity;

public enum OrderStatus {
	PENDING,//("Chờ xử lý"),
    PROCESSING,//("Đang xử lý"),
    SHIPPED,//("Đã gửi hàng"),
    DELIVERED,//("Đã giao hàng"),
    CANCELLED,//("Đã hủy"),
    REFUNDED//("Đã hoàn tiền"); 
}