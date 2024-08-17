package com.ta2khu75.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.request.BrandRequest;
import com.ta2khu75.dto.response.BrandResponse;
import com.ta2khu75.entity.Brand;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.exception.NotFoundException;
import com.ta2khu75.mapper.BrandMapper;
import com.ta2khu75.repository.BrandRepository;
import com.ta2khu75.service.BrandService;
import com.ta2khu75.util.FileUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService {
	BrandRepository repo;
	BrandMapper mppr;

	@Override
	public BrandResponse create(BrandRequest req, MultipartFile file) throws IOException {
		Brand brand = mppr.toMdl(req);
		brand.setImage(FileUtil.save(FileEnviroment.UPLOAD_BRAND, file));
		return mppr.toRes(repo.save(brand));
	}

	@Override
	public BrandResponse read(Integer id) {
		return mppr.toRes(repo.findById(id).orElseThrow(() -> new NotFoundException("Could not find brand with id: " + id)));
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<BrandResponse> readAll() {
		return repo.findAll().stream().map(mppr::toRes).toList();
	}

	@Override
	public BrandResponse update(Integer id, BrandRequest req, MultipartFile file) throws IOException {
		Brand brand = repo.findById(id).orElseThrow(() -> new NotFoundException("Could not find brand with id: " + id));
		mppr.update(req, brand);
		if (file != null) {
			if (brand.getImage() != null) {
				FileUtil.save(FileEnviroment.UPLOAD_BRAND, brand.getImage(), file);
			} else {
				brand.setImage(FileUtil.save(FileEnviroment.UPLOAD_BRAND, file));
			}
		}
		return mppr.toRes(repo.save(brand));
	}

}
