package com.projects.projects_api.exception;

public class UserException {

    public static class UserAlreadyExists extends RuntimeException {
        public UserAlreadyExists(String username) {
            super("User " + username + " already exists.");
        }
    }
}
