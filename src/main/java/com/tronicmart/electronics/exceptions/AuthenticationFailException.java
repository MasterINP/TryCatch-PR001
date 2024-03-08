package com.tronicmart.electronics.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
    public AuthenticationFailException(String message) {
        super(message);
    }
}
