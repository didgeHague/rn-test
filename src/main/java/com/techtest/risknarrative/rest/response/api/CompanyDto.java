package com.techtest.risknarrative.rest.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.risknarrative.rest.response.truproxy.Company;
import com.techtest.risknarrative.rest.response.truproxy.Officer;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyDto(AddressDto address,
                         @JsonProperty("company_number")
                         String companyNumber,
                         @JsonProperty("company_type")
                         String companyType,
                         String title,
                         @JsonProperty("company_status")
                         String companyStatus,
                         @JsonProperty("date_of_creation")
                         String dateOfCreation,
                         List<OfficerDto> officers) {

    public static CompanyDto from(Company company, List<Officer> officers) {
        List<OfficerDto> officerDtos = new ArrayList<>();
        if (officers != null && !officers.isEmpty()) {
            officers.forEach(officer ->
                    officerDtos.add(OfficerDto.from(officer))
            );
        }
        return new CompanyDto(AddressDto.from(company.address()),
                company.companyNumber(),
                company.companyType(),
                company.title(),
                company.companyStatus(),
                company.dateOfCreation(),
                officerDtos
        );
    }
}
