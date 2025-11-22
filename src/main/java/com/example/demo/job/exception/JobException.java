package com.example.demo.job.exception;

import com.example.demo.global.exception.ErrorType;
import lombok.Getter;

@Getter
public class JobException extends RuntimeException{

    private final ErrorType errorType;

    public JobException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
