package com.api.server.exceptions;

public class ErrorInWebClientHttpRequestException extends Exception{
    public ErrorInWebClientHttpRequestException(String message) {
        super(message);
    }
}
