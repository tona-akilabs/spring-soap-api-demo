package com.example.spring_soap_api_demo.helper;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.slf4j.Logger;

import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LocalDateAdapter.class);

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        // return java.time.LocalDate.parse(v);
        LOGGER.info("Unmarshalling date: {}", v);
        return (v == null || v.isEmpty()) ? null : LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        // return v.toString();
        LOGGER.info("Marshalling date: {}", v);
        return (v == null) ? null : v.toString();
    }
}
