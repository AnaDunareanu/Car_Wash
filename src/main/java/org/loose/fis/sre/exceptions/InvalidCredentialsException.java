package org.loose.fis.sre.exceptions;

public class InvalidCredentialsException extends Exception {

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Please enter valid username and password!"));
    }
}
