package com.example.spring_soap_api_demo.request;

import com.example.spring_soap_api_demo.helper.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlRootElement(name = "createProject", namespace = "http://example.com/project")
public class ProjectRequest {
    private String name;
    private LocalDate createdDate;

    public ProjectRequest() {
    }

    @XmlElement(name = "name", namespace = "http://example.com/project")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "createdDate", namespace = "http://example.com/project")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
