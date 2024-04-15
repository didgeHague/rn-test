package com.techtest.risknarrative.rest.response.truproxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Company(Address address,
                      @JsonProperty("company_number")
                      String companyNumber,
                      @JsonProperty("company_type")
                      String companyType,
                      String title,
                      @JsonProperty("company_status")
                      String companyStatus,
                      @JsonProperty("date_of_creation")
                      String dateOfCreation) {
}
