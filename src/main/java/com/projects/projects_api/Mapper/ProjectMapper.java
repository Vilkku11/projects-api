package com.projects.projects_api.Mapper;

import com.projects.projects_api.dto.ProjectDto;
import com.projects.projects_api.dto.UserDto;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.model.Project;

public class ProjectMapper {
    public ProjectDto toDto(Project project) {
        return new ProjectDto(project.getName(), project.getDescription());
    }

    public Project toProject(ProjectDto projectDto, Long userId) {
        Project project = new Project();
        project.setName(projectDto.name());
        project.setDescription(project.getDescription());
        project.setUserId(userId);
        return project;
    }
}
