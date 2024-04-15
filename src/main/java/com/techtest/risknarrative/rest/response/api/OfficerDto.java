package com.techtest.risknarrative.rest.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.risknarrative.rest.response.truproxy.Address;
import com.techtest.risknarrative.rest.response.truproxy.Officer;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OfficerDto(String name,
                         @JsonProperty("officer_role")
                      String officerRole,
                         @JsonProperty("appointed_on")
                      String appointedOn,
                         AddressDto address) {

    public static OfficerDto from(Officer officer){
        return new OfficerDto(
                officer.name(),
                officer.officerRole(),
                officer.appointedOn(),
                AddressDto.from(officer.address())
        );
    }
}
