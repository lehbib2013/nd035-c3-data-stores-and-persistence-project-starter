package com.udacity.jdnd.course3.critter.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class PetNotFound extends RuntimeException{
    public PetNotFound() {

    }
    public PetNotFound(String message) {
        super(message);
    }
}
