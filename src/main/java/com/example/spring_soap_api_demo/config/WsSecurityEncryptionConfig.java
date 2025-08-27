package com.example.spring_soap_api_demo.config;

import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.Merlin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import java.util.List;
import java.util.Properties;

@Configuration
public class WsSecurityEncryptionConfig {
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

        // Encryption settings
        securityInterceptor.setSecurementActions(WSHandlerConstants.ENCRYPT); // ENCRYPT, SIGN, etc.
        securityInterceptor.setSecurementEncryptionUser("server-key"); // alias of certificate in keystore
        securityInterceptor.setSecurementEncryptionParts("{Content}{http://example.com}GetCountryRequest");
        securityInterceptor.setSecurementEncryptionCrypto(createCrypto());

        // Validation settings (for incoming messages)
        securityInterceptor.setValidationActions(WSHandlerConstants.ENCRYPT);
        securityInterceptor.setValidationDecryptionCrypto(createCrypto());

        return securityInterceptor;
    }


    @Bean
    public Crypto createCrypto() throws Exception {
        // Merlin crypto = new Merlin();

        // Load keystore as InputStream
        ClassPathResource resource = new ClassPathResource("server.jks");

        Properties props = new Properties();
        props.setProperty("org.apache.wss4j.crypto.merlin.keystore.type", "JKS");
        props.setProperty("org.apache.wss4j.crypto.merlin.keystore.password", "storepass");
        props.setProperty("org.apache.wss4j.crypto.merlin.keystore.alias", "server-key");
        props.setProperty("org.apache.wss4j.crypto.merlin.keystore.file", resource.getFile().getAbsolutePath());

        Crypto crypto = new Merlin(
                props,
                Thread.currentThread().getContextClassLoader(),
                null // no password encryptor
        );
        return crypto;
    }
}
