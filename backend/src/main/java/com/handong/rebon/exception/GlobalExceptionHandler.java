package com.handong.rebon.exception;

import com.handong.rebon.exception.dto.ExceptionResponse;
import com.handong.rebon.exception.dto.OAuthLoginFailErrorResponse;
import com.handong.rebon.exception.oauth.NoSuchOAuthMemberException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
}
