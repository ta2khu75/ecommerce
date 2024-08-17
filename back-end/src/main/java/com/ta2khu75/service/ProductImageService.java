package com.ta2khu75.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.request.ProductImageRequest;
import com.ta2khu75.dto.response.ProductImageResponse;

public interface ProductImageService {
	ProductImageResponse create(ProductImageRequest req, MultipartFile file) throws IOException;
	ProductImageResponse update(String nameFile,MultipartFile file) throws IOException;
	void delete(String nameFile);
	List<ProductImageResponse> readAllProductId(Long productId);
}
