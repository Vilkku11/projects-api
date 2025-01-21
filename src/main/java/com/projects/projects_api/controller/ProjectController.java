package com.projects.projects_api.controller;


import com.projects.projects_api.exception.ProjectException;
import com.projects.projects_api.model.Project;
import com.projects.projects_api.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectRepository repository;

    ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/projects")
    List<Project> all() {
        return repository.findAll();
    }

    @PostMapping("/projects")
        Project newProject(@RequestBody Project newProject) {
            return repository.save(newProject);
        }

    @GetMapping("/projects/{id}")
    Project one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProjectException.ProjectNotFoundException(id));
    }
}










