package com.elliothutchinson.cmdbranch.jobrun.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> handleDoesNotExist(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new Error("Resource not found"), HttpStatus.NOT_FOUND);
    }
}
