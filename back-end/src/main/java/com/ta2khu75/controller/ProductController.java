package com.ta2khu75.controller;

import java.io.IOException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.ta2khu75.dto.PageResponse;
import com.ta2khu75.dto.request.ProductRequest;
import com.ta2khu75.dto.response.ProductDetailsResponse;
import com.ta2khu75.dto.response.ProductResponse;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.service.ProductService;
import com.ta2khu75.util.FileUtil;
import com.ta2khu75.util.ObjectUtil;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
	ProductService svc;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<ProductResponse> create(@RequestPart("product_request") String productRequestString,
			@RequestPart(required = true) MultipartFile file) throws IOException {
		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("File is empty");
		}
		ProductRequest productRequest = ObjectUtil.convertToObject(productRequestString, ProductRequest.class);
		return ApiResponse.<ProductResponse>builder().data(svc.create(productRequest, file)).build();
	}

	@GetMapping
	public ApiResponse<PageResponse<ProductResponse>> readAll(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String brand, @RequestParam(required = false) Integer category,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size,
			@RequestParam(required = false) Double min, @RequestParam(required = false) Double max) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return ApiResponse.<PageResponse<ProductResponse>>builder()
				.data(svc.readPage(keyword, brand, category, min, max, pageable)).build();
	}

	@GetMapping("{id}")
	public ApiResponse<ProductDetailsResponse> read(@PathVariable Long id) {
		return ApiResponse.<ProductDetailsResponse>builder().data(svc.read(id)).build();
	}

	@PutMapping("{id}")
	public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @ModelAttribute ProductRequest entity,
			@RequestPart MultipartFile file) throws IOException {
		return ApiResponse.<ProductResponse>builder().data(svc.update(id, entity, file)).build();
	}

	@DeleteMapping("{id}")
	public ApiResponse<Void> delete(@PathVariable Long id) {
		svc.delete(id);
		return ApiResponse.<Void>builder().message("Delete successfully").build();
	}

	@GetMapping("image/{fileName}")
	public ResponseEntity<StreamingResponseBody> getImage(@PathVariable String fileName) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(FileUtil.read(FileEnviroment.UPLOAD_PRODUCT + fileName));
	}
}
