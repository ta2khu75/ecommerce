package com.ta2khu75.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.request.CategoryRequest;
import com.ta2khu75.dto.response.CategoryResponse;

public interface CategoryService {
	CategoryResponse create(CategoryRequest request, MultipartFile image) throws IOException;
	CategoryResponse read(Integer id);
	void delete(Integer id);
	List<CategoryResponse> readAll();
	CategoryResponse update(Integer id, CategoryRequest request, MultipartFile image) throws IOException;
	List<CategoryResponse> readAllByLevelAndName(String keyword, Integer level);
}
