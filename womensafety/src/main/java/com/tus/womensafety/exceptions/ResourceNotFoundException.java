package com.tus.womensafety.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message){
        super(message);
    }

    private boolean suppressStacktrace = false;

    public ResourceNotFoundException(String message, boolean suppressStacktrace) {
        super(message, null, suppressStacktrace, !suppressStacktrace);
        this.suppressStacktrace = suppressStacktrace;
    }

    @Override
    public String toString() {
        if (suppressStacktrace) {
            return getLocalizedMessage();
        } else {
            return super.toString();
        }
    }
}
