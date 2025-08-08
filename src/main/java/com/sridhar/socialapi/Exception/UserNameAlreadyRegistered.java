package com.sridhar.socialapi.Exception;

public class UserNameAlreadyRegistered extends RuntimeException{

    public UserNameAlreadyRegistered(String message) {
        super(message);
    }

}
