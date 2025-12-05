package com.sorcerer.jwt_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> illegalArugementErrorHandler(IllegalArgumentException exception){
    ErrorDto errorDto=ErrorDto.builder().errorMessage(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(errorDto,errorDto.getStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exceptionHandler(Exception exception){
        ErrorDto errorDto=ErrorDto.builder().errorMessage(exception.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(errorDto,errorDto.getStatus());
    }

}
