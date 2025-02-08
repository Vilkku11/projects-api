package com.projects.projects_api.controller;


import com.projects.projects_api.dto.ProjectDto;
import com.projects.projects_api.exception.ProjectException;
import com.projects.projects_api.model.Project;
import com.projects.projects_api.service.ProjectService;
import com.projects.projects_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectRepository repository;

    private final ProjectService projectService;

    ProjectController(ProjectRepository repository, ProjectService projectService) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    List<Project> all() {
        return repository.findAll();
    }

    @PostMapping("/projects")
        ProjectDto newProject(@RequestBody ProjectDto newProject) {
            return projectService.addProject(newProject);
        }

    @GetMapping("/projects/{id}")
    ProjectDto one(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @PutMapping("/projects/{id}")
    ProjectDto replaceProject(@RequestBody ProjectDto projectDto, @PathVariable Long id) {
        return projectService.modifyData(projectDto, id);
    }

    @DeleteMapping("/projects/{id}")
    ProjectDto deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}










