package com.ta2khu75.dto.response;


import com.ta2khu75.entity.Category;

public record ProductResponse(Long id, String name, Double price, Integer remaining, String image, boolean active, Category category, BrandResponse brand) {//, List<ProductImageRes> productImages) {
}

