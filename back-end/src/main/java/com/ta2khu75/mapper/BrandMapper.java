package com.ta2khu75.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.dto.request.BrandRequest;
import com.ta2khu75.dto.response.BrandResponse;
import com.ta2khu75.entity.Brand;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface BrandMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "products", ignore = true)
	Brand toMdl(BrandRequest req);
	BrandResponse toRes(Brand brand);
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "products", ignore = true)
	void update(BrandRequest req, @MappingTarget Brand brand);
}
