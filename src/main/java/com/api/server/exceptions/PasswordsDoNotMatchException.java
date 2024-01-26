package com.api.server.exceptions;

import java.lang.Exception;

public class PasswordsDoNotMatchException extends Exception{
    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
