package com.ta2khu75.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	LocalDateTime createdDate;
	@Enumerated(EnumType.STRING)
	OrderStatus orderStatus=OrderStatus.PENDING;
	@LastModifiedDate
	@Column(insertable = false)
	LocalDateTime updatedDate;
	@OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;
	@ManyToOne
	Account account;
	@ManyToOne
	DeliveryAddress deliveryAddress;
}
