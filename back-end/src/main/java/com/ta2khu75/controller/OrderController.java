package com.ta2khu75.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.dto.request.OrderRequest;
import com.ta2khu75.dto.response.OrderResponse;
import com.ta2khu75.group.CreateGroup;
import com.ta2khu75.service.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/order")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
	OrderService svc;

	@PostMapping
	public OrderResponse create(@Validated(value = CreateGroup.class) @RequestBody OrderRequest req) {
		return svc.create(req);
	}

	@GetMapping("{id}")
	public OrderResponse read(@PathVariable Long id) {
		return svc.readById(id);
	}

	@GetMapping("account/{accountEmail}")
    public Page<OrderResponse> readByAccountEmail(@PathVariable String accountEmail, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(required = false, defaultValue = "8") int size) {
		Pageable pageable=PageRequest.of(page, size);
        return svc.readByAccountEmail(accountEmail,pageable);
    }
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
	@PutMapping("account/{id}")
	public OrderResponse update(@PathVariable Long id, @Validated(value = CreateGroup.class) @RequestBody OrderRequest req) {
        return svc.update(id, req);
    }
}
