package com.example.spring_soap_api_demo.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "helloWorldRequest", namespace = "http://example.com/helloworld")
public class HelloWorldRequest {
    private String name;

    public HelloWorldRequest() {}

    @XmlElement(name = "name", namespace = "http://example.com/helloworld")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
