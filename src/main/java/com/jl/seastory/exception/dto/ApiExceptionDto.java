package com.jl.seastory.exception.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiExceptionDto {
    private int state;
    private String message;

    public ApiExceptionDto(HttpStatus status, String message){
        this.message = message;
        this.state = status.value();
    }
}
