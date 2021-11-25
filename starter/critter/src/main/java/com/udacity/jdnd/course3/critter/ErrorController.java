package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.services.EmployeeNotFound;
import com.udacity.jdnd.course3.critter.services.OwnerNotFound;
import com.udacity.jdnd.course3.critter.services.PetNotFound;
import com.udacity.jdnd.course3.critter.services.ScheduleNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_VALIDATION_FAILED_MESSAGE = "Validation failed";

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(
                        Collectors.toList());

        ApiError apiError = new ApiError(DEFAULT_VALIDATION_FAILED_MESSAGE, errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(Exception.class)
   public final  ResponseEntity<Object> handleAll(Exception ex,WebRequest request ) {
         ApiError apiErr = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(apiErr,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EmployeeNotFound.class)
    public final  ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFound ex, WebRequest request ) {
        ApiError apiErr = new ApiError(new Date(), ex.getMessage(),  request.getDescription(false));
        return new ResponseEntity(apiErr,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PetNotFound.class)
    public final  ResponseEntity<Object> handleEmployeeNotFound(PetNotFound ex, WebRequest request ) {
        ApiError apiErr = new ApiError(new Date(), ex.getMessage(),  request.getDescription(false));
        return new ResponseEntity(apiErr,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OwnerNotFound.class)
    public final  ResponseEntity<Object> handleEmployeeNotFound(OwnerNotFound ex, WebRequest request ) {
        ApiError apiErr = new ApiError(new Date(), ex.getMessage(),  request.getDescription(false));
        return new ResponseEntity(apiErr,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ScheduleNotFound.class)
    public final  ResponseEntity<Object> handleEmployeeNotFound(ScheduleNotFound ex, WebRequest request ) {
        ApiError apiErr = new ApiError(new Date(), ex.getMessage(),  request.getDescription(false));
        return new ResponseEntity(apiErr,HttpStatus.NOT_FOUND);
    }
}
