package com.userservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHanlderController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(java.net.SocketTimeoutException.class)
    protected ResponseEntity<Object> socketTimeoutException(
            java.net.SocketTimeoutException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.REQUEST_TIMEOUT, request);
    }
}
