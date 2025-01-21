package com.projects.projects_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectException.ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String projectNotFoundHandler(ProjectException.ProjectNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ProjectException.ProjectNameInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String projectNameInvalidHandler(ProjectException.ProjectNameInvalidException ex) {
        return ex.getMessage();
    }
}
