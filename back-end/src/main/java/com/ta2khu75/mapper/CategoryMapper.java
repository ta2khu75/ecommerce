package com.ta2khu75.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.dto.request.CategoryRequest;
import com.ta2khu75.dto.response.CategoryResponse;
import com.ta2khu75.entity.Category;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	@Mapping(target = "parent", ignore = true)
	@Mapping(target = "children", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "products", ignore = true)
	Category toEntity(CategoryRequest request);
	@Mapping(target = "parent", ignore = true)
	@Mapping(target = "children", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "products", ignore = true)
	void update(CategoryRequest request, @MappingTarget Category category);
	CategoryResponse toResponse(Category category);
}
