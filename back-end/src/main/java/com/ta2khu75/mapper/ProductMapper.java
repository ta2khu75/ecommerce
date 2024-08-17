package com.ta2khu75.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.ta2khu75.dto.request.ProductRequest;
import com.ta2khu75.dto.response.ProductDetailsResponse;
import com.ta2khu75.dto.response.ProductResponse;
import com.ta2khu75.entity.Brand;
import com.ta2khu75.entity.Product;
import com.ta2khu75.entity.ProductImage;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	@Mapping(target = "category", ignore = true)
	@Mapping(target = "basePrice", ignore = true)
	@Mapping(target = "productOptionTypes", ignore = true)
	@Mapping(target = "productVariants", ignore = true)
	@Mapping(target = "active", ignore = true)
	@Mapping(target = "brand", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "productImages", ignore = true)
	Product toProduct(ProductRequest req);
	
	@Mapping(target = "category", ignore = true)
	@Mapping(target = "basePrice", ignore = true)
	@Mapping(target = "productOptionTypes", ignore = true)
	@Mapping(target = "productVariants", ignore = true)
	@Mapping(target = "brand", ignore = true)
	@Mapping(target = "active", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "productImages", ignore = true)
	void update(ProductRequest req, @MappingTarget Product product);
	ProductResponse toRes(Product product);
	
//	@Mapping(source = "productImages", target ="productImages")// qualifiedByName = "mapProductImages")
	ProductDetailsResponse toProductDetails(Product product);
	default List<String> mapProductImages(List<ProductImage> productImages) {
        return productImages.stream()
                .map(ProductImage::getNameFile) // Assuming ProductImage has a getUrl() method
                .collect(Collectors.toList());
    }
	default String mapBrand(Brand brand) {
		return brand.getName();
	}
}
