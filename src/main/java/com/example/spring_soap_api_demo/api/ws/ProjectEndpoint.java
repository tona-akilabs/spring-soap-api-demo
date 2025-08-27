package com.example.spring_soap_api_demo.api.ws;

import com.example.spring_soap_api_demo.entity.Project;
import com.example.spring_soap_api_demo.repository.ProjectRepository;
import com.example.spring_soap_api_demo.api.request.ProjectRequest;
import com.example.spring_soap_api_demo.api.response.ListProjectResponse;
import com.example.spring_soap_api_demo.api.response.ProjectResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ProjectEndpoint {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(ProjectEndpoint.class);

    private static final String NAMESPACE_URI = "http://example.com/project";

    @Autowired
    private ProjectRepository projectRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "listProject")
    @ResponsePayload
    public ListProjectResponse getRecords() {
        ListProjectResponse wrapper = new ListProjectResponse();
        wrapper.setProjects(getAllProjects());
        return wrapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createProject")
    @ResponsePayload
    public ProjectResponse createRecord(@RequestPayload ProjectRequest request) {
        log.info("Received request for name: {}", request.getName());
        log.info("Received request for createdDate: {}", request.getCreatedDate());
        createProject(request);
        ProjectResponse response = new ProjectResponse();
        response.setName(request.getName());
        response.setCreatedDate(request.getCreatedDate().toString());
        response.setStatus("PROJECT CREATED");
        return response;
    }

    private List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectResponse> responses = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponse response = new ProjectResponse();
            response.setName(project.getName());
            response.setCreatedDate(project.getCreationDate().toString());
            response.setStatus("Done");
            responses.add(response);
        }
        return responses;
    }
    private void createProject(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setCreationDate(request.getCreatedDate());

        projectRepository.save(project);
        log.info("Project saved with ID: {}", project.getId());
    }
}
