package com.example.spring_soap_api_demo;

import com.example.spring_soap_api_demo.request.HelloWorldRequest;
import com.example.spring_soap_api_demo.response.HelloWorldResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldEndpointTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Test
    public void testSayHello() {
        HelloWorldRequest request = new HelloWorldRequest();
        request.setName("John");

        String url = "http://localhost:" + port + "/ws";
        HelloWorldResponse response = (HelloWorldResponse) webServiceTemplate.marshalSendAndReceive(url, request);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Hello, John!");
    }
}
