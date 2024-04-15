package com.techtest.risknarrative;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
@AutoConfigureMockMvc
class RisknarrativeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCompaniesSearch_CompanyNumberPresent() throws Exception {
        // Stubbing WireMock -
        stubFor(get(urlEqualTo("/Companies/v1/Search?Query=06500244"))
                .withHeader("x-api-key", matching("an-api-key"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(COMPANY_RESULT)));

        stubFor(get(urlEqualTo("/Companies/v1/Officers?CompanyNumber=06500244"))
                .withHeader("x-api-key", matching("an-api-key"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(OFFICER_RESULT)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/company-search")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-api-key", "an-api-key")
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "companyName" : "BBC LIMITED",
                            "companyNumber" : "06500244"
                        }
                        """)
        ).andExpect(MockMvcResultMatchers.content().json(SEARCH_RESULT, false));
    }

    @Test
    public void testCompaniesSearch_CompanyNamePresent() throws Exception {
        // Stubbing WireMock - ideally have these stubs in files
        stubFor(get(urlEqualTo("/Companies/v1/Search?Query=BBC%20LIMITED"))
                .withHeader("x-api-key", matching("an-api-key"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(COMPANY_RESULT)));

        stubFor(get(urlEqualTo("/Companies/v1/Officers?CompanyNumber=06500244"))
                .withHeader("x-api-key", matching("an-api-key"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(OFFICER_RESULT)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/company-search")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-api-key", "an-api-key")
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "companyName" : "BBC LIMITED"
                        }
                        """)
        ).andExpect(MockMvcResultMatchers.content().json(SEARCH_RESULT, false));
    }

    //ideally have these stubs in files
    private static final String COMPANY_RESULT = """
            {
                "page_number": 1,
                "kind": "search#companies",
                "total_results": 20,
                "items": [
                    {
                        "company_status": "active",
                        "address_snippet": "Boswell Cottage Main Street, North Leverton, Retford, England, DN22 0AD",
                        "date_of_creation": "2008-02-11",
                        "matches": {
                            "title": [
                                1,
                                3
                            ]
                        },
                        "description": "06500244 - Incorporated on 11 February 2008",
                        "links": {
                            "self": "/company/06500244"
                        },
                        "company_number": "06500244",
                        "title": "BBC LIMITED",
                        "company_type": "ltd",
                        "address": {
                            "premises": "Boswell Cottage Main Street",
                            "postal_code": "DN22 0AD",
                            "country": "England",
                            "locality": "Retford",
                            "address_line_1": "North Leverton"
                        },
                        "kind": "searchresults#company",
                        "description_identifier": [
                            "incorporated-on"
                        ]
                    }]
              }
            """;

    private static final String OFFICER_RESULT = """
            {
                      "etag": "cffabac57398072eab8130a896e54094b9aa3c25",
                      "links": {
                          "self": "/company/06500244/officers"
                      },
                      "kind": "officer-list",
                      "items_per_page": 35,
                      "items": [
                          {
                              "address": {
                                  "premises": "5",
                                  "postal_code": "SW20 0DP",
                                  "country": "England",
                                  "locality": "London",
                                  "address_line_1": "Cranford Close"
                              },
                              "name": "BOXALL, Sarah Victoria",
                              "appointed_on": "2008-02-11",
                              "officer_role": "secretary",
                              "links": {
                                  "officer": {
                                      "appointments": "/officers/t3kASKOIrQoe-k_JpPyJ7LYEAqE/appointments"
                                  }
                              },
                              "occupation": "Hr Manager",
                              "nationality": "British"
                          },
                          {
                              "address": {
                                  "premises": "5",
                                  "postal_code": "SW20 0DP",
                                  "country": "England",
                                  "locality": "London",
                                  "address_line_1": "Cranford Close"
                              },
                              "name": "BRAY, Simon Anton",
                              "appointed_on": "2008-02-11",
                              "officer_role": "director",
                              "links": {
                                  "officer": {
                                      "appointments": "/officers/43iRdrOR91V6QdAb0PMDS3eEGg8/appointments"
                                  }
                              },
                              "date_of_birth": {
                                  "month": 1,
                                  "year": 1967
                              },
                              "occupation": "Builder",
                              "country_of_residence": "England",
                              "nationality": "English"
                          },
                          {
                              "address": {
                                  "postal_code": "M7 4AS",
                                  "locality": "Manchester",
                                  "address_line_1": "39a Leicester Road",
                                  "address_line_2": "Salford"
                              },
                              "name": "FORM 10 SECRETARIES FD LTD",
                              "appointed_on": "2008-02-11",
                              "officer_role": "corporate-nominee-secretary",
                              "links": {
                                  "officer": {
                                      "appointments": "/officers/Yg4rTn5QucYg_hJOxGTnx3B51WY/appointments"
                                  }
                              },
                              "resigned_on": "2008-02-12"
                          },
                          {
                              "address": {
                                  "postal_code": "M7 4AS",
                                  "locality": "Manchester",
                                  "address_line_1": "39a Leicester Road",
                                  "address_line_2": "Salford"
                              },
                              "name": "FORM 10 DIRECTORS FD LTD",
                              "appointed_on": "2008-02-11",
                              "officer_role": "corporate-nominee-director",
                              "links": {
                                  "officer": {
                                      "appointments": "/officers/aDjhOpnMaB_uAHDxRnMLWpa9C-I/appointments"
                                  }
                              },
                              "resigned_on": "2008-02-12"
                          }
                      ],
                      "active_count": 2,
                      "total_results": 4,
                      "resigned_count": 2
                  }
            """;

    private static final String SEARCH_RESULT = """
            {
                 "total_results": 1,
                 "items": [
                     {
                         "company_number": "06500244",
                         "company_type": "ltd",
                         "company_status": "active",
                         "date_of_creation": "2008-02-11",
                         "address": {
                             "postal_code": "DN22 0AD",
                             "address_line_1": "North Leverton",
                             "locality": "Retford",
                             "premises": "Boswell Cottage Main Street",
                             "country": "England"
                         },
                         "title": "BBC LIMITED",
                         "officers": [
                             {
                                 "officer_role": "secretary",
                                 "appointed_on": "2008-02-11",
                                 "name": "BOXALL, Sarah Victoria",
                                 "address": {
                                     "postal_code": "SW20 0DP",
                                     "address_line_1": "Cranford Close",
                                     "locality": "London",
                                     "premises": "5",
                                     "country": "England"
                                 }
                             },
                             {
                                 "officer_role": "director",
                                 "appointed_on": "2008-02-11",
                                 "name": "BRAY, Simon Anton",
                                 "address": {
                                     "postal_code": "SW20 0DP",
                                     "address_line_1": "Cranford Close",
                                     "locality": "London",
                                     "premises": "5",
                                     "country": "England"
                                 }
                             }
                         ]
                     }
                 ]
             }
            """;
}
