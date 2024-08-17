package com.ta2khu75.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ta2khu75.dto.ApiResponse;
import com.ta2khu75.dto.request.CategoryRequest;
import com.ta2khu75.dto.response.CategoryResponse;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.service.CategoryService;
import com.ta2khu75.util.FileUtil;
import com.ta2khu75.util.ObjectUtil;
import com.ta2khu75.util.ValidatorInjectionUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
	CategoryService service;
	ValidatorInjectionUtil validatorInjectionUtil;
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<CategoryResponse> createCategory(@RequestPart("category") String request,
			@RequestPart MultipartFile image) throws IOException {
		CategoryRequest categoryRequest = ObjectUtil.convertToObject(request, CategoryRequest.class);
		validatorInjectionUtil.validation(categoryRequest);
		if (!FileUtil.isNonEmpty(image)) {
			throw new IllegalArgumentException("File must be not empty");
		}
		return ApiResponse.<CategoryResponse>builder().data(service.create(categoryRequest, image)).build();
	}
	@GetMapping("image/{imageName}")
	public ResponseEntity<StreamingResponseBody> getImage(@PathVariable String imageName) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(FileUtil.read(FileEnviroment.UPLOAD_CATEGORY + imageName));
    }
	@PutMapping("{id}")
	public ApiResponse<CategoryResponse> updateCategory(@PathVariable Integer id, @RequestPart("category") String request,
            @RequestPart(required = false) MultipartFile image) throws IOException {
		CategoryRequest categoryRequest = ObjectUtil.convertToObject(request, CategoryRequest.class);
		validatorInjectionUtil.validation(categoryRequest);
		return ApiResponse.<CategoryResponse>builder().data( service.update(id, categoryRequest, image)).build();
	}
    @GetMapping("{id}")
    public ApiResponse<CategoryResponse> readCategory(@PathVariable Integer id) {
        return ApiResponse.<CategoryResponse>builder().data(service.read(id)).build();
    }
    @GetMapping("level-name")
    public ApiResponse<List<CategoryResponse>> readCategoryByLevelAndName(@RequestParam Integer level, @RequestParam(required = false) String name) {
        return ApiResponse.<List<CategoryResponse>>builder().data(service.readAllByLevelAndName(name, level)).build();
    }
    @GetMapping
    public ApiResponse<List<CategoryResponse>> readAllCategory() {
        return ApiResponse.<List<CategoryResponse>>builder().data(service.readAll()).build();
    }
    @DeleteMapping("{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.<Void>builder().message("Delete category successfully").build();
    }
//    @PutMapping("{id}")
//    public ApiResponse<CategoryResponse> updateCategoryImage(@PathVariable Integer id, @RequestPart("category") String request, @RequestPart MultipartFile image) throws IOException {
//        CategoryRequest categoryRequest=ObjectUtil.convertToObject(request, CategoryRequest.class);
//    	return ApiResponse.<CategoryResponse>builder().data(service.update(id, categoryRequest, image)).build();
//    }
}
