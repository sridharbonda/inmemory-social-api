package com.sridhar.socialapi.exception;

public class UserNameAlreadyRegisteredException extends RuntimeException{

    public UserNameAlreadyRegisteredException(String message) {
        super(message);
    }

}
