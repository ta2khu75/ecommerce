package com.ta2khu75.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, String>{
	List<ProductImage> findByProductId(Long productId);
}
