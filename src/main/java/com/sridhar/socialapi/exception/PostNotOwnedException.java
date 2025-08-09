package com.sridhar.socialapi.exception;

public class PostNotOwnedException extends RuntimeException{
    public PostNotOwnedException (String message) {
         super(message);
    }
}
