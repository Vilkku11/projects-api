package com.projects.projects_api.service;

import com.projects.projects_api.mapper.ProjectMapper;
import com.projects.projects_api.dto.ProjectDto;
import com.projects.projects_api.exception.ProjectException;
import com.projects.projects_api.exception.UserException;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.model.Project;
import com.projects.projects_api.repository.ProjectRepository;
import com.projects.projects_api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper projectMapper;
    private final ObjectValidator objectValidator;
    private final UserService userService;

    ProjectService(ProjectRepository repository, ProjectMapper projectMapper, ObjectValidator objectValidator, UserService userService) {
        this.repository = repository;
        this.projectMapper = projectMapper;
        this.objectValidator = objectValidator;
        this.userService = userService;
    }

    public void validateProjectName(Project project) {
        if (project.getName() == null || project.getName().isEmpty()) {
            throw new ProjectException.ProjectNameInvalidException("Project name must not be empty");
        }
    }

    public ProjectDto getProject(Long id){
        Project project = repository.findById(id)
                .orElseThrow(() -> new ProjectException.ProjectNotFoundException(id));

        MyUser user = userService.getUser();
        if (!Objects.equals(project.getUserId(), user.getId())){
            throw new ProjectException.ProjectNotFoundException(project.getId());
        }

        return projectMapper.toDto(project);
    }

    public ProjectDto addProject(ProjectDto projectDto){
        validate(projectDto);


        Project newProject = projectMapper.toProject(projectDto, userService.getUser().getId());

        validateProjectName(newProject);

        repository.save(newProject);

        return getProject(newProject.getId());
    }

    public ProjectDto modifyData(ProjectDto projectDto, Long id) {
        validate(projectDto);
        MyUser user = userService.getUser();

        return repository.findById(id)
                .map(project -> {
                    if (!Objects.equals(project.getUserId(), user.getId())){
                        throw new ProjectException.ProjectNotFoundException(project.getId());
                    }

                    if (projectDto.name() != null) {
                        project.setName(projectDto.name());
                    }

                    if (projectDto.description() != null) {
                        project.setDescription(projectDto.description());
                    }
                    return projectMapper.toDto(repository.save(project));
                        })
                .orElseGet(() -> {
                   return addProject(projectDto);
                });
    }

    public ProjectDto deleteProject(Long id){
        Project project = repository.findById(id)
                .orElseThrow(() -> new ProjectException.ProjectNotFoundException(id));

        MyUser user = userService.getUser();
        if (!Objects.equals(project.getUserId(), user.getId())){
            throw new ProjectException.ProjectNotFoundException(project.getId());
        }
        ProjectDto projectDto = projectMapper.toDto(project);
        repository.deleteById(id);
        return projectDto;
    }

    // For now
    private void validate(ProjectDto projectDto) {
        var violations = objectValidator.validate(projectDto);
        if(!violations.isEmpty()) {
            throw new UserException.InvalidUsernameOrPassword();
        }
    }
}
