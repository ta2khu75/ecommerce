package com.ta2khu75.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.PageResponse;
import com.ta2khu75.dto.request.ProductRequest;
import com.ta2khu75.dto.response.ProductDetailsResponse;
import com.ta2khu75.dto.response.ProductResponse;
import com.ta2khu75.entity.Category;
import com.ta2khu75.entity.Product;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.exception.NotFoundException;
import com.ta2khu75.mapper.ProductMapper;
import com.ta2khu75.repository.BrandRepository;
import com.ta2khu75.repository.CategoryRepository;
import com.ta2khu75.repository.ProductRepository;
import com.ta2khu75.service.ProductService;
import com.ta2khu75.util.FileUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
	ProductRepository repo;
	ProductMapper mppr;
	BrandRepository brandRepo;
	CategoryRepository categoryRepo;

	@Override
	public ProductResponse create(ProductRequest req, MultipartFile file) throws IOException {
		Product product = mppr.toProduct(req);
		product.setBrand(brandRepo.findById(req.brandId())
				.orElseThrow(() -> new NotFoundException("Could not find Brand with name: " + req.brandId())));
		product.setImage(FileUtil.save(FileEnviroment.UPLOAD_PRODUCT, file));
		return mppr.toRes(repo.save(product));
	}

	@Override
	public ProductDetailsResponse read(Long id) {
		return mppr.toProductDetails(
				repo.findById(id).orElseThrow(() -> new NotFoundException("Could not find Product with id: " + id)));
	}

	@Override
	public void delete(Long id) {
		Product product = repo.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not find Product with id: " + id));
		product.setActive(false);
		repo.save(product);
	}

	@Override
	public List<ProductResponse> readAll() {
		return repo.findAll().stream().map(mppr::toRes).toList();
	}

	@Override
	public PageResponse<ProductResponse> readPage(String keyword, String brand, Integer categoryId, Double min,
			Double max, Pageable pageable) {
		Category category = null;
		if (categoryId != null) {
			category = categoryRepo.findById(categoryId).orElse(null);
		}
		Page<ProductResponse> page=repo.readPage(keyword, brand, category, min, max, pageable).map(mppr::toRes);
		return new PageResponse<>(pageable.getPageNumber()+1, page.getTotalPages(), page.getTotalElements(), page.getContent());// repo.readPage(keyword, brand, category, min, max, pageable).map(mppr::toRes);
	}

	@Override
	public ProductResponse update(Long id, ProductRequest req, MultipartFile file) throws IOException {
		Product product = repo.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not find Product with id: " + id));
		mppr.update(req, product);
		if (!product.getBrand().getName().equals(req.brandId())) {
			product.setBrand(brandRepo.findById(req.brandId())
					.orElseThrow(() -> new NotFoundException("Could not find Brand with name: " + req.brandId())));
		}
		if (file != null) {
			if (product.getImage() != null) {
				FileUtil.save(FileEnviroment.UPLOAD_PRODUCT, product.getImage(), file);
			} else {
				product.setImage(FileUtil.save(FileEnviroment.UPLOAD_PRODUCT, file));
			}
		}
		return mppr.toRes(repo.save(product));
	}
}
