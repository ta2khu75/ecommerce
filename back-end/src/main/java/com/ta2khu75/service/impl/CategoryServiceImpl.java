package com.ta2khu75.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.dto.request.CategoryRequest;
import com.ta2khu75.dto.response.CategoryResponse;
import com.ta2khu75.entity.Category;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.exception.NotFoundException;
import com.ta2khu75.mapper.CategoryMapper;
import com.ta2khu75.repository.CategoryRepository;
import com.ta2khu75.service.CategoryService;
import com.ta2khu75.util.FileUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
	CategoryRepository repository;
	CategoryMapper mapper;
	@Override
	public CategoryResponse create(CategoryRequest request, MultipartFile image) throws IOException {
		Category category=mapper.toEntity(request);
		category.setImage(FileUtil.save(FileEnviroment.UPLOAD_CATEGORY, image));
		if(category.getLevel()!=0) {
			Category parent=repository.findById(request.parent()).orElseThrow(() -> new NotFoundException("Parent category not found"));
            category.setParent(parent);
		}
		return mapper.toResponse(repository.save(category));
	}
	@Override
	public CategoryResponse read(Integer id) {
		return mapper.toResponse(repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found")));
	}
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	@Override
	public List<CategoryResponse> readAll() {
		return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
	}
	@Override
	public CategoryResponse update(Integer id, CategoryRequest request, MultipartFile image) throws IOException {
		Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
		mapper.update(request, category);
		if(FileUtil.isNonEmpty(image)) {
			FileUtil.save(FileEnviroment.UPLOAD_CATEGORY, category.getImage(), image);
		}
		if((category.getLevel()!=request.level()||category.getLevel()==0) && request.level()>0) {
			Category parent = repository.findById(request.parent()).orElseThrow(()->new NotFoundException("Category parent not found"));
			category.setParent(parent);
		}
		return mapper.toResponse(repository.save(category));
	}
	@Override
	public List<CategoryResponse> readAllByLevelAndName(String keyword, Integer level) {
		return repository.findAllByLevelOrName(level, keyword).stream().map(mapper::toResponse).collect(Collectors.toList());
	}
}
