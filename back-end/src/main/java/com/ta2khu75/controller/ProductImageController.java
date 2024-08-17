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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ta2khu75.dto.request.ProductImageRequest;
import com.ta2khu75.dto.response.ProductImageResponse;
import com.ta2khu75.enviroment.FileEnviroment;
import com.ta2khu75.service.ProductImageService;
import com.ta2khu75.util.FileUtil;
import com.ta2khu75.util.ObjectUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/product-image")
public class ProductImageController {
	ProductImageService svc;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProductImageResponse create(@RequestPart("product_image") String req, @RequestPart MultipartFile file)
			throws IOException {
		ProductImageRequest productImageRequest = ObjectUtil.convertToObject(req, ProductImageRequest.class);
		return svc.create(productImageRequest, file);
	}

	@GetMapping("{productId}")
	public List<ProductImageResponse> readAllByProductId(@PathVariable Long productId) {
		return svc.readAllProductId(productId);
	}

	@GetMapping("image/{fileName}")
	public ResponseEntity<StreamingResponseBody> getImage(@PathVariable String fileName) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(FileUtil.read(FileEnviroment.UPLOAD_PRODUCT_IMAGE + fileName));
	}

	@PutMapping("{fileName}")
	public ProductImageResponse update(@PathVariable String fileName, @RequestPart MultipartFile file)
			throws IOException {
		return svc.update(fileName, file);
	}

	@DeleteMapping("{fileName}")
	public void delete(@PathVariable String fileName) {
		svc.delete(fileName);
	}
}
