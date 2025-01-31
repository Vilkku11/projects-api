package com.projects.projects_api.exception;

public class UserException {

    public static class UserAlreadyExists extends RuntimeException {
        public UserAlreadyExists(String username) {
            super("User " + username + " already exists.");
        }
    }

    public static class InvalidUsernameOrPassword extends RuntimeException {
        public InvalidUsernameOrPassword() {
            super("Invalid username or password");
        }
    }


}
