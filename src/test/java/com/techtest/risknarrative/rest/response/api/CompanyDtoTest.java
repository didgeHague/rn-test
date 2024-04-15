package com.techtest.risknarrative.rest.response.api;

import com.techtest.risknarrative.rest.response.truproxy.Address;
import com.techtest.risknarrative.rest.response.truproxy.Company;
import com.techtest.risknarrative.rest.response.truproxy.Officer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDtoTest {

    @Test
    public void test_From_sets_the_correct_fields() {
        Address address = new Address("1", "2", "3", "4", "5");
        Company company = new Company(address, "1a", "2b", "3c", "4d", "5e");
        Officer officer = new Officer("a", "b", "c", null, address);

        CompanyDto companyDto = CompanyDto.from(company, List.of(officer));
        assertEquals("1a", companyDto.companyNumber());
        assertEquals("2b", companyDto.companyType());
        assertEquals("3c", companyDto.title());
        assertEquals("4d", companyDto.companyStatus());
        assertEquals("5e", companyDto.dateOfCreation());

        AddressDto dto = companyDto.address();
        assertEquals("1", dto.locality());
        assertEquals("2", dto.postCode());
        assertEquals("3", dto.premises());
        assertEquals("4", dto.addressLine1());
        assertEquals("5", dto.country());

        OfficerDto officerDto = companyDto.officers().get(0);
        assertEquals("a", officerDto.name());
        assertEquals("b", officerDto.officerRole());
        assertEquals("c", officerDto.appointedOn());

        AddressDto officerAddr = officerDto.address();
        assertEquals("1", officerAddr.locality());
        assertEquals("2", officerAddr.postCode());
        assertEquals("3", officerAddr.premises());
        assertEquals("4", officerAddr.addressLine1());
        assertEquals("5", officerAddr.country());
    }
}