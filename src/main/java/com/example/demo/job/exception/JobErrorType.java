package com.example.demo.job.exception;

import com.example.demo.global.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JobErrorType implements ErrorType {

    JOB_NOT_FOUND(HttpStatus.NOT_FOUND,
            "해당하는 직무가 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
