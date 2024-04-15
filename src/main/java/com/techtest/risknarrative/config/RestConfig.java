package com.techtest.risknarrative.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {
    @Value("${api.host.baseurl}")
    private String apiHost;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl(apiHost).build();
    }
}
