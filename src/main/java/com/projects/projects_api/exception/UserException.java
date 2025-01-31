package com.projects.projects_api.exception;

public class UserException {

    public static class UserAlreadyExists extends RuntimeException {
        public UserAlreadyExists(String username) {
            super("User " + username + " already exists.");
        }
    }

    public static class InvalidUsernameOrPassword extends RuntimeException {
        public InvalidUsernameOrPassword() {
            super("Invalid username or password.");
        }
    }

    public static class PasswordTooShort extends  RuntimeException {
        public PasswordTooShort(){ super("Password needs to be at least 5 characters long.");}
    }

    public static class UserSaveFail extends  RuntimeException {
        public UserSaveFail() {super("There was an error on creating account");}
    }
}
