package com.projects.projects_api.repository;

import com.projects.projects_api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {


}
