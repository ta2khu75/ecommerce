package com.ta2khu75.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "product_evaluations")
public class ProductEvaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
    Integer rating;
	@Column(nullable = false)
    String comment;
	@Column(nullable = false)
    boolean anonymous;
	@ManyToOne
    Account account;
	@ManyToOne
	ProductVariant productVariant;
	@ManyToOne
	OrderItem orderItem;
}
