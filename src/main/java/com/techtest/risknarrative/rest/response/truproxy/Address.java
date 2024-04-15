package com.techtest.risknarrative.rest.response.truproxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Address(String locality,
                      @JsonProperty("postal_code")
                      String postCode,
                      String premises,
                      @JsonProperty("address_line_1")
                      String addressLine1,
                      String country) {
}
