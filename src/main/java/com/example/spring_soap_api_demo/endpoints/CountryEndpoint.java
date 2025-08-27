package com.example.spring_soap_api_demo.endpoints;


import com.example.spring_soap_api_demo.request.GetCountryRequest;
import com.example.spring_soap_api_demo.response.GetCountryResponse;
import com.example.spring_soap_api_demo.model.Country;
import org.slf4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(CountryEndpoint.class);

    private static final String NAMESPACE_URI = "http://example.com/country";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        logger.info("Received request for country: " + request.getName());
        GetCountryResponse response = new GetCountryResponse();
        Country country = new Country();
        country.setName(request.getName());
        country.setPopulation(1000000);
        response.setCountry(country);
        return response;
    }
}
