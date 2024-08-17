package com.ta2khu75.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ta2khu75.dto.ApiResponse;
import com.ta2khu75.dto.request.BrandRequest;
import com.ta2khu75.dto.response.BrandResponse;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.service.BrandService;
import com.ta2khu75.util.FileUtil;
import com.ta2khu75.util.ObjectUtil;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/brand")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {
	BrandService svc;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<BrandResponse> create(@Valid @RequestPart String brand, @RequestPart MultipartFile file)
			throws IOException {
		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("File is empty");
		}
		BrandRequest req = ObjectUtil.convertToObject(brand, BrandRequest.class);
		return ApiResponse.<BrandResponse>builder().data(svc.create(req, file)).build();
	}

	@GetMapping
	public ApiResponse<List<BrandResponse>> readAll() {
		return ApiResponse.<List<BrandResponse>>builder().data(svc.readAll()).build();
	}

	@GetMapping("image/{fileName}")
	public ResponseEntity<StreamingResponseBody> getImage(@PathVariable String fileName) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(FileUtil.read(FileEnviroment.UPLOAD_BRAND + fileName));
	}

	@GetMapping("{id}")
	public ApiResponse<BrandResponse> read(@PathVariable Integer id) {
		return ApiResponse.<BrandResponse>builder().data(svc.read(id)).build();
	}

	@PutMapping("{id}")
	public ApiResponse<BrandResponse> update(@PathVariable Integer id, @RequestPart String brand, @RequestPart(required = false) MultipartFile file) throws IOException {
		BrandRequest req = ObjectUtil.convertToObject(brand, BrandRequest.class);
		return ApiResponse.<BrandResponse>builder().data(svc.update(id, req, file)).build();
	}

	@DeleteMapping("{id}")
	public ApiResponse<Void> delete(@PathVariable Integer id) {
		svc.delete(id);
		return ApiResponse.<Void>builder().message("Delete brand successfully").build();

	}

}
