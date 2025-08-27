package com.example.spring_soap_api_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import java.util.List;

@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    private final Wss4jSecurityInterceptor securityInterceptor;

    public WebServiceConfig(Wss4jSecurityInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor);
    }
}
