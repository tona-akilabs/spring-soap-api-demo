package com.example.spring_soap_api_demo.api.response;


import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@XmlRootElement(namespace = "http://example.com/project")
public class ProjectResponse {
    private String name;
    private String createdDate;
    private String status;

    public ProjectResponse() {}

    public ProjectResponse(String name, String createdDate, String status) {
        this.name = name;
        this.createdDate = createdDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
