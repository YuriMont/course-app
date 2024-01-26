package com.api.server.exceptions;

public class EmailOrPasswordNotMatchesException extends Exception {
    public EmailOrPasswordNotMatchesException(String message) {
        super(message);
    }
}
