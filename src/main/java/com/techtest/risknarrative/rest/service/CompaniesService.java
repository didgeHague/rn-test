package com.techtest.risknarrative.rest.service;

import com.techtest.risknarrative.rest.response.truproxy.CompanyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CompaniesService {

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Value("${api.companies.uri}")
    private String companiesSearchUri;

    @Value("${api.companies.search.param}")
    private String companiesSearchParam;
    private final RestClient restClient;

    public CompaniesService(RestClient restClient) {
        this.restClient = restClient;
    }

    public CompanyResponse searchForCompanyByName(String companyName, String apiKey) {
        RestClient.ResponseSpec retrieve = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(companiesSearchUri)
                        .queryParam(companiesSearchParam, companyName)
                        .build())
                .header(apiKeyHeader, apiKey)
                .retrieve();
        return retrieve.body(CompanyResponse.class);
    }

    public CompanyResponse searchForCompanyByNumber(String companyNumber, String apiKey) {
        RestClient.ResponseSpec retrieve = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(companiesSearchUri)
                        .queryParam(companiesSearchParam, companyNumber)
                        .build())
                .header(apiKeyHeader, apiKey)
                .retrieve();
        return retrieve.body(CompanyResponse.class);
    }
}
