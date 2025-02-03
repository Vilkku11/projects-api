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
            projectService.addProject(newProject);
            projectService.validateProjectName(newProject);
            return repository.save(newProject);
        }

    @GetMapping("/projects/{id}")
    ProjectDto one(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @PutMapping("/projects/{id}")
    Project replaceProject(@RequestBody Project newProject, @PathVariable Long id) {

        return repository.findById(id)
                .map(project -> {
                     // at least for now...
                    if (newProject.getName() != null) {
                        project.setName(newProject.getName());
                    }
                    if (newProject.getDescription() != null) {
                        project.setDescription(newProject.getDescription());
                    }
                    return repository.save(project);
                })
                .orElseGet(() -> {
                    projectService.validateProjectName(newProject);
                    return repository.save(newProject);
                });
    }

    @DeleteMapping("/projects/{id}")
    void deleteProject(@PathVariable Long id) {
        repository.deleteById(id);
    }
}










