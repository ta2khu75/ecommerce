package com.ta2khu75.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.PageResponse;
import com.ta2khu75.dto.request.ProductRequest;
import com.ta2khu75.dto.response.ProductDetailsResponse;
import com.ta2khu75.dto.response.ProductResponse;

public interface ProductService {
	ProductResponse create(ProductRequest req, MultipartFile file) throws IOException;
	ProductDetailsResponse read(Long id);
	void delete(Long id);
	ProductResponse update(Long id, ProductRequest req, MultipartFile file) throws IOException;
	List<ProductResponse> readAll();
	PageResponse<ProductResponse> readPage( String keyword, String brand, Integer categoryId, Double min, Double max, Pageable pageable);
}
