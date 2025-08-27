package com.example.spring_soap_api_demo.request;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getCountryRequest", namespace = "http://example.com/country")
public class GetCountryRequest {
    private String name;

    @XmlElement(name = "name", namespace = "http://example.com/country")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
