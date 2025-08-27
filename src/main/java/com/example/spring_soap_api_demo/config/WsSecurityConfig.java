package com.example.spring_soap_api_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;

import java.util.Properties;

@Configuration
public class WsSecurityConfig extends WsConfigurerAdapter {
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();

        // Validate incoming UsernameToken
        interceptor.setValidationActions("UsernameToken");
        interceptor.setValidationCallbackHandler(callbackHandler());

        // Add UsernameToken to outgoing messages
        /*interceptor.setSecurementActions("UsernameToken");
        interceptor.setSecurementUsername("soapuser");
        interceptor.setSecurementPassword("soappass");*/

        return interceptor;
    }

    @Bean
    public SimplePasswordValidationCallbackHandler callbackHandler() {
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        Properties users = new Properties();
        users.setProperty("soapuser", "soappass"); // username=soapuser, password=soappass
        handler.setUsers(users);
        return handler;
    }

    @Override
    public void addInterceptors(java.util.List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }
}
