package com.example.spring_soap_api_demo.endpoints;

import com.example.spring_soap_api_demo.request.HelloWorldRequest;
import com.example.spring_soap_api_demo.request.ProjectRequest;
import com.example.spring_soap_api_demo.response.HelloWorldResponse;
import com.example.spring_soap_api_demo.response.ProjectResponse;
import org.slf4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProjectEndpoint {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(ProjectEndpoint.class);

    private static final String NAMESPACE_URI = "http://example.com/project";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "projectRequest")
    @ResponsePayload
    public ProjectResponse sayHello(@RequestPayload ProjectRequest request) {
        log.info("Received request for name: {}", request.getName());
        log.info("Received request for createdDate: {}", request.getCreatedDate());
        ProjectResponse response = new ProjectResponse();
        response.setName(request.getName());
        response.setCreatedDate(request.getCreatedDate().toString());
        response.setStatus("PROJECT CREATED");
        return response;
    }
}
