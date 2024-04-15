package com.techtest.risknarrative.rest.response.api;

import com.techtest.risknarrative.rest.response.truproxy.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDtoTest {

    @Test
    public void test_From_sets_the_correct_fields(){
        Address address = new Address("1", "2", "3", "4", "5");
        AddressDto dto = AddressDto.from(address);
        assertEquals("1", dto.locality());
        assertEquals("2", dto.postCode());
        assertEquals("3", dto.premises());
        assertEquals("4", dto.addressLine1());
        assertEquals("5", dto.country());
    }
}