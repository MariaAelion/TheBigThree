package com.todoitproject.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerConfig {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        throw e;
    }
    
    @ExceptionHandler(IOException.class) 
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) 
    public Object exceptionHandler(IOException e, HttpServletRequest request) { 
    	return new HttpEntity<>(e.getMessage()); 
    }
}