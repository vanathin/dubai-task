package com.ekar.demo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorControllerAdvice {

    public static final String CLIENT_ERROR = "CLIENT_ERROR";
    public static final String SERVER_ERROR = "SERVER_ERROR";

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionResponse handleAllException(final Throwable ex) {
        return ExceptionResponse.builder()
                .errorCode(SERVER_ERROR)
                .errorMessages(Collections.singletonList(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ExceptionResponse validationException(final MethodArgumentNotValidException exception) {
        return ExceptionResponse.builder()
                .errorCode(CLIENT_ERROR)
                .errorMessages(exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> String.format("%s: %s",
                                fieldError.getField(),
                                fieldError.getDefaultMessage()))
                        .collect(Collectors.toList()))
                .build();
    }
}
