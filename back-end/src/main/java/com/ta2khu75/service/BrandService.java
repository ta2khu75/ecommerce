package com.ta2khu75.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.request.BrandRequest;
import com.ta2khu75.dto.response.BrandResponse;

public interface BrandService {
	BrandResponse create(BrandRequest req, MultipartFile file) throws IOException;
	BrandResponse read(Integer id);
	void delete(Integer id);
	List<BrandResponse> readAll();
	BrandResponse update(Integer id, BrandRequest req, MultipartFile file) throws IOException;
}
