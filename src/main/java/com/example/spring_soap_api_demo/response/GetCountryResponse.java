package com.example.spring_soap_api_demo.response;

import com.example.spring_soap_api_demo.model.Country;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://example.com/country")
public class GetCountryResponse {
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
