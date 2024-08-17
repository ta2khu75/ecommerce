package com.ta2khu75.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.entity.Category;
import com.ta2khu75.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE "
			+ "(:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) "
			+ "AND (:brand IS NULL OR p.brand.name = :brand)" + "AND (:category IS NULL OR p.category = :category) "
			+ "AND (:minPrice IS NULL OR p.basePrice>= :minPrice) " + "AND (:maxPrice IS NULL OR p.basePrice <= :maxPrice) ")
	Page<Product> readPage(@Param("keyword") String keyword, @Param("brand") String brand,
			@Param("category") Category category, @Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice, Pageable pageable);

	Page<Product> findByBrandName(String brandName, Pageable pageable);
}
