package com.techtest.risknarrative.rest.response.truproxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyResponse(List<Company> items) {
}
