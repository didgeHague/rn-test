package com.techtest.risknarrative.rest.service;

import com.techtest.risknarrative.rest.response.truproxy.OfficerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OfficersService {

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Value("${api.officers.uri}")
    private String officersSearchUri;

    @Value("${api.officers.search.param}")
    private String officersSearchParam;
    private final RestClient restClient;

    public OfficersService(RestClient restClient) {
        this.restClient = restClient;
    }

    public OfficerResponse searchForCompanyByNumber(String companyNumber, String apiKey) {
        RestClient.ResponseSpec retrieve = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(officersSearchUri)
                        .queryParam(officersSearchParam, companyNumber)
                        .build())
                .header(apiKeyHeader, apiKey)
                .retrieve();
        return retrieve.body(OfficerResponse.class);
    }
}
