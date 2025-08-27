package com.example.spring_soap_api_demo.endpoints;

import com.example.spring_soap_api_demo.request.HelloWorldRequest;
import com.example.spring_soap_api_demo.response.HelloWorldResponse;
import org.slf4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloWorldEndpoint {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(HelloWorldEndpoint.class);

    private static final String NAMESPACE_URI = "http://example.com/helloworld";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "helloWorldRequest")
    @ResponsePayload
    public HelloWorldResponse sayHello(@RequestPayload HelloWorldRequest request) {
        log.info("Received request for name: {}", request.getName());
        HelloWorldResponse response = new HelloWorldResponse();
        response.setMessage("Hello, " + request.getName() + "!");
        return response;
    }
}
