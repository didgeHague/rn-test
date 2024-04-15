package com.techtest.risknarrative.rest.response.truproxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Officer(String name,
                      @JsonProperty("officer_role")
                      String officerRole,
                      @JsonProperty("appointed_on")
                      String appointedOn,
                      @JsonProperty("resigned_on")
                      String resignedOn,
                      Address address) {
}
