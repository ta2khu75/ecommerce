package com.ta2khu75.dto.response.details;

import java.util.List;

import com.ta2khu75.dto.response.CategoryResponse;

public record CategoryDetailsResponse(Integer id, String name, int level, String image, List<CategoryResponse> categories) {
}
