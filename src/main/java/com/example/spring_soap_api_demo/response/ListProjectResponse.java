package com.example.spring_soap_api_demo.response;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(namespace = "http://example.com/project")
public class ListProjectResponse {
    private List<ProjectResponse> projects;

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectResponse> projects) {
        this.projects = projects;
    }
}
