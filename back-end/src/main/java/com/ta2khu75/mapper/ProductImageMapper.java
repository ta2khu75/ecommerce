package com.ta2khu75.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.dto.response.ProductImageResponse;
import com.ta2khu75.entity.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
	ProductImageResponse toRes(ProductImage productImage);
}
