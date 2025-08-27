package com.example.spring_soap_api_demo.config;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomPasswordValidationCallbackHandler implements CallbackHandler {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(CustomPasswordValidationCallbackHandler.class);

    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> users = new HashMap<>();

    public CustomPasswordValidationCallbackHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        // store encoded password
        users.put("soapuser", passwordEncoder.encode("soappass"));

        logger.info("Encoded password for 'soappass': " + passwordEncoder.encode("soappass"));
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) callback;
                String username = pc.getIdentifier();
                String password = pc.getPassword();

                logger.info("Authenticating user: " + username);
                logger.info("Provided password: " + password);
                logger.info("Stored encoded password: " + users.get(username));

                String encodedPassword = users.get(username);

                /*if (encodedPassword == null || !passwordEncoder.matches(password, encodedPassword)) {
                    throw new SecurityException("Invalid username or password");
                }*/
                // For PasswordDigest, set the plain password for WSS4J to validate
                if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                    // You must store the plain password or retrieve it securely
                    String plainPassword = "soappass"; // Replace with secure retrieval
                    pc.setPassword(plainPassword);
                } else {
                    // For PasswordText, validate directly
                    String providedPassword = pc.getPassword();
                    if (!passwordEncoder.matches(providedPassword, encodedPassword)) {
                        throw new SecurityException("Invalid password");
                    }
                }
            } else {
                throw new UnsupportedCallbackException(callback, "Unrecognized Callback");
            }
        }
    }
}
