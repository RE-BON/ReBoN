package com.handong.rebon.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RebonException.class)
    public ResponseEntity<ExceptionResponse> rebonException(RebonException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ExceptionResponse(e.getMessage()));
    }
}