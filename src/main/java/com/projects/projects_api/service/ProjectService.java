package com.projects.projects_api.service;

import com.projects.projects_api.exception.ProjectException;
import com.projects.projects_api.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    public void validateProjectName(Project project) {
        if (project.getName() == null || project.getName().isEmpty()) {
            throw new ProjectException.ProjectNameInvalidException("Project name must not be empty");
        }
    }
}
