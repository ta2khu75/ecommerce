package com.ta2khu75.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.request.ProductImageRequest;
import com.ta2khu75.dto.response.ProductImageResponse;
import com.ta2khu75.entity.Product;
import com.ta2khu75.entity.ProductImage;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.exception.NotFoundException;
import com.ta2khu75.mapper.ProductImageMapper;
import com.ta2khu75.repository.ProductImageRepository;
import com.ta2khu75.repository.ProductRepository;
import com.ta2khu75.service.ProductImageService;
import com.ta2khu75.util.FileUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageServiceImpl implements ProductImageService{
	ProductRepository productRepo;
	ProductImageRepository repo;
	ProductImageMapper mppr;
	@Override
	public ProductImageResponse create(ProductImageRequest req, MultipartFile file) throws IOException {
		Product product=productRepo.findById(req.productId()).orElseThrow(() -> new NotFoundException("Could not find product with id: "+req.productId()));
		return mppr.toRes(repo.save(new ProductImage(FileUtil.save(FileEnviroment.UPLOAD_PRODUCT_IMAGE, file),product)));
	}
	@Override
	public ProductImageResponse update(String nameFile,MultipartFile file) throws IOException {
		ProductImage productImage=repo.findById(nameFile).orElseThrow(() -> new NotFoundException("Could not find product with name file: "+nameFile));
		FileUtil.save(FileEnviroment.UPLOAD_PRODUCT_IMAGE, nameFile, file);
		return mppr.toRes(productImage);
	}
	@Override
	public void delete(String nameFile) {
		repo.deleteById(nameFile);
	}
	@Override
	public List<ProductImageResponse> readAllProductId(Long productId) {
		return repo.findByProductId(productId).stream().map(mppr::toRes).toList();
	}
}
