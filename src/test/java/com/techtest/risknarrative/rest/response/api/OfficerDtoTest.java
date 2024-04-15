package com.techtest.risknarrative.rest.response.api;

import com.techtest.risknarrative.rest.response.truproxy.Address;
import com.techtest.risknarrative.rest.response.truproxy.Officer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficerDtoTest {

    @Test
    public void test_From_sets_the_correct_fields() {
        Officer officer = new Officer("a", "b", "c", "d",
                new Address("1", "2", "3", "4", "5"));

        OfficerDto officerDto = OfficerDto.from(officer);
        assertEquals("a", officerDto.name());
        assertEquals("b", officerDto.officerRole());
        assertEquals("c", officerDto.appointedOn());

        AddressDto dto = officerDto.address();
        assertEquals("1", dto.locality());
        assertEquals("2", dto.postCode());
        assertEquals("3", dto.premises());
        assertEquals("4", dto.addressLine1());
        assertEquals("5", dto.country());
    }
}