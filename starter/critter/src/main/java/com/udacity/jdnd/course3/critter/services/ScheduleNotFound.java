package com.udacity.jdnd.course3.critter.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class ScheduleNotFound extends RuntimeException {
    public ScheduleNotFound() {

    }
    public ScheduleNotFound(String message) {
        super(message);
    }
}
