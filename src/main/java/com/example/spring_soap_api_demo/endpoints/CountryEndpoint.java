package com.example.spring_soap_api_demo.endpoints;


import com.example.spring_soap_api_demo.country.Country;
import com.example.spring_soap_api_demo.country.GetCountryRequest;
import com.example.spring_soap_api_demo.country.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/country";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        Country country = new Country();
        country.setName(request.getName());
        country.setPopulation(1000000);
        response.setCountry(country);
        return response;
    }
}
