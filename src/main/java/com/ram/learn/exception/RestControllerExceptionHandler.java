package com.ram.learn.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.sq.demo")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.badRequest().body("{\"error\": \"someError\"}");
    }
}
