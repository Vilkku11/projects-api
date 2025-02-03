package com.projects.projects_api.service;

import com.projects.projects_api.Mapper.ProjectMapper;
import com.projects.projects_api.dto.ProjectDto;
import com.projects.projects_api.dto.UserDto;
import com.projects.projects_api.exception.ProjectException;
import com.projects.projects_api.exception.UserException;
import com.projects.projects_api.model.Project;
import com.projects.projects_api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper projectMapper;
    private final ObjectValidator objectValidator;

    ProjectService(ProjectRepository repository, ProjectMapper projectMapper, ObjectValidator objectValidator) {
        this.repository = repository;
        this.projectMapper = projectMapper;
        this.objectValidator = objectValidator;
    }

    public void validateProjectName(Project project) {
        if (project.getName() == null || project.getName().isEmpty()) {
            throw new ProjectException.ProjectNameInvalidException("Project name must not be empty");
        }
    }

    public ProjectDto getProject(Long id){
        Project project = repository.findById(id)
                .orElseThrow(() -> new ProjectException.ProjectNotFoundException(id));
        return projectMapper.toDto(project);
    }

    public ProjectDto addProject(ProjectDto projectDto){
        validate(projectDto);
        
        Project newProject = projectMapper.toProject(projectDto);
    }


    private void validate(ProjectDto projectDto) {
        var violations = objectValidator.validate(projectDto);
        if(!violations.isEmpty()) {
            throw new UserException.InvalidUsernameOrPassword();
        }
    }
}
