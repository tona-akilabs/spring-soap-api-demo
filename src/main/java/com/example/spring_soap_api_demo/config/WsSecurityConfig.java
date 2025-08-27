package com.example.spring_soap_api_demo.config;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;

import javax.security.auth.callback.CallbackHandler;
import java.util.Properties;

@Configuration
public class WsSecurityConfig extends WsConfigurerAdapter {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(WsSecurityConfig.class);
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
        // users.setProperty("alice", "password123"); // username=alice, password=password123
        handler.setUsers(users);
        return handler;
    }

    /*@Bean
    public CallbackHandler callbackHandler() {
        return new CustomPasswordValidationCallbackHandler();
    }*/

    @Override
    public void addInterceptors(java.util.List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }
}
