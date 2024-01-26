package com.api.server.exceptions;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException(String message) {
        super(message);
    }
}
