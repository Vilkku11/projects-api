package com.projects.projects_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProjectDto(
        @NotNull
        @NotEmpty
        String name,

        String description
) {
}
