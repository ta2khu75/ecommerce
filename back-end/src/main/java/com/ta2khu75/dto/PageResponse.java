package com.ta2khu75.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse <T>{
	int page;
	@JsonProperty("total_pages")
	int totalPages;
	@JsonProperty("total_results")
	Long totalResults;
	List<T> results;
}
