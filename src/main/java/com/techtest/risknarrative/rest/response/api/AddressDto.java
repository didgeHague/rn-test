package com.techtest.risknarrative.rest.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.risknarrative.rest.response.truproxy.Address;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDto(String locality,
                         @JsonProperty("postal_code")
                      String postCode,
                         String premises,
                         @JsonProperty("address_line_1")
                      String addressLine1,
                         String country) {

    public static AddressDto from(Address address){
        return new AddressDto(address.locality(),
                address.postCode(),
                address.premises(),
                address.addressLine1(),
                address.country());
    }
}
