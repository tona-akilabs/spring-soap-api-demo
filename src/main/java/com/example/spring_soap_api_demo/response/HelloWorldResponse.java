package com.example.spring_soap_api_demo.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "helloWorldResponse", namespace = "http://example.com/helloworld")
public class HelloWorldResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
