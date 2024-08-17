package com.ta2khu75.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findAllByLevel(Integer level);

	@Query("SELECT c FROM Category c WHERE "
			+ "(:level IS NULL OR c.level=:level) AND (:keyword IS NULL OR c.name LIKE %:keyword%)")
	List<Category> findAllByLevelOrName(@Param("level") Integer level, @Param("keyword") String name);
}
