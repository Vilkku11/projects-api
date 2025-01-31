package com.projects.projects_api.exception;

public class ProjectException {

    public static class ProjectNotFoundException extends RuntimeException {
        public ProjectNotFoundException(Long id) {
            super("Could not find project " +id + ".");
        }
    }

    public static class ProjectNameInvalidException extends RuntimeException {
        public ProjectNameInvalidException(String text) {
            super(text);
        }
    }
}
