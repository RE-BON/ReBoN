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

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchOAuthMemberException.class)
    public ResponseEntity<OAuthLoginFailErrorResponse> oAuthLoginFailHandler(NoSuchOAuthMemberException e) {
        logger.info("Login Fail : " + e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(OAuthLoginFailErrorResponse.from(e));
    }

    @ExceptionHandler(RebonException.class)
    public ResponseEntity<ExceptionResponse> rebonException(RebonException e) {

        if (e.getHttpStatus().is4xxClientError()) {
            logger.info("Client Error : " + e.getMessage());
        } else if (e.getHttpStatus().is5xxServerError()) {
            logger.error("Server Error : " + e.getMessage());
        }
        return ResponseEntity.status(e.getHttpStatus())
                             .body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> commonException(Exception e) {

        logger.error("Unknown Exception : " + e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new ExceptionResponse(e.getMessage()));
    }
}
