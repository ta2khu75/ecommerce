package com.ta2khu75.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ta2khu75.entity.Category;

public record ProductDetailsResponse(Long id, String name, Double price, Integer remaining, String image, boolean active, String description, Category category, String brand, @JsonProperty("product_images") List<String> productImages) {
}
