package com.projects.projects_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



public record UserDto (
        @NotNull
        @NotEmpty
        String username,

        @NotNull
        @NotEmpty
        String password){

}
