package com.example.demo.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorType implements ErrorType {


    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "The email is already in use."),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "The password is incorrect."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member not found.")
    ;

    private final HttpStatus status;

    private final String message;
}
