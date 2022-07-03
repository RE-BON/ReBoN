package com.handong.rebon.exception;

import com.handong.rebon.RebonApplication;
import com.handong.rebon.exception.dto.ExceptionResponse;
import com.handong.rebon.exception.dto.OAuthLoginFailErrorResponse;
import com.handong.rebon.exception.oauth.NoSuchOAuthMemberException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RebonApplication.class);

    @ExceptionHandler(NoSuchOAuthMemberException.class)
    public ResponseEntity<OAuthLoginFailErrorResponse> oAuthLoginFailHandler(NoSuchOAuthMemberException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(OAuthLoginFailErrorResponse.from(exception));
    }

    @ExceptionHandler(RebonException.class)
    public ResponseEntity<ExceptionResponse> rebonException(RebonException e) {
        return ResponseEntity.status(e.getHttpStatus())
                             .body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> commonException(Exception e) {
        logger.error("Unknown Exception : " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ExceptionResponse(e.getMessage()));
    }
}
