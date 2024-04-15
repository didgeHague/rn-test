package com.techtest.risknarrative.rest.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CompanySearchResponse(
        @JsonProperty("total_results")
        Integer totalResults,
        List<CompanyDto> items) {
}
