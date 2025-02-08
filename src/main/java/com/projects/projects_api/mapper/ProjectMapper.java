package com.projects.projects_api.mapper;

import com.projects.projects_api.dto.ProjectDto;
import com.projects.projects_api.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectDto toDto(Project project) {
        return new ProjectDto(project.getId(), project.getName(), project.getDescription());
    }

    public Project toProject(ProjectDto projectDto, Long userId) {
        return new Project(projectDto.name(), projectDto.description(), userId);
    }
}
