package com.ekar.demo.app.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ExceptionResponse {

    private String errorCode;
    private List<String> errorMessages;
}
