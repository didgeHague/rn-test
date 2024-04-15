package com.techtest.risknarrative.rest;


import com.techtest.risknarrative.rest.request.CompanySearchRequest;
import com.techtest.risknarrative.rest.response.api.CompanyDto;
import com.techtest.risknarrative.rest.response.api.CompanySearchResponse;
import com.techtest.risknarrative.rest.response.truproxy.Company;
import com.techtest.risknarrative.rest.response.truproxy.CompanyResponse;
import com.techtest.risknarrative.rest.response.truproxy.Officer;
import com.techtest.risknarrative.rest.response.truproxy.OfficerResponse;
import com.techtest.risknarrative.rest.service.CompaniesService;
import com.techtest.risknarrative.rest.service.OfficersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CompaniesController {

    @Value("${api.key.header}")
    private String apiKeyHeader;

    private final CompaniesService companiesService;
    private final OfficersService officersService;

    @Autowired
    public CompaniesController(CompaniesService companiesService, OfficersService officersService) {
        this.companiesService = companiesService;
        this.officersService = officersService;
    }

    @PostMapping(path = "api/company-search")
    public ResponseEntity<CompanySearchResponse> companySearch(@RequestBody CompanySearchRequest request,
                                                               @RequestHeader Map<String, String> headers) {
        String apiKey = headers.get(apiKeyHeader);
        if (!StringUtils.hasLength(apiKey)) return ResponseEntity.badRequest().build();

        final CompanyResponse companyResponse;
        if (StringUtils.hasLength(request.companyNumber())) {
            companyResponse = companiesService.searchForCompanyByNumber(request.companyNumber(), apiKey);
        } else if(StringUtils.hasLength(request.companyName())) {
            companyResponse = companiesService.searchForCompanyByName(request.companyName(), apiKey);
        } else {
            return ResponseEntity.badRequest().build();
        }

        if (companyResponse == null) {
            return ResponseEntity.internalServerError().build();
        }

        List<Company> items = companyResponse.items();
        if (items == null || items.isEmpty()) {
            return ResponseEntity.ok(new CompanySearchResponse(0, List.of()));
        }

        List<CompanyDto> companyDtos = new ArrayList<>();
        items.parallelStream().forEach(company -> {
            OfficerResponse officerResponse = officersService.searchForCompanyByNumber(company.companyNumber(), apiKey);
            List<Officer> officers = officerResponse!= null ? officerResponse.items() : List.of();
            List<Officer> activeOfficers = officers.stream()
                    .filter(officer -> !StringUtils.hasLength(officer.resignedOn()))
                    .collect(Collectors.toList());
            companyDtos.add(CompanyDto.from(company, activeOfficers));
        });
        return ResponseEntity.ok(new CompanySearchResponse(companyDtos.size(), companyDtos));
    }
}
