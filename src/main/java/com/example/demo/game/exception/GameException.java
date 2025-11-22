package com.example.demo.game.exception;

import com.example.demo.global.exception.ErrorType;
import lombok.Getter;

@Getter
public class GameException extends RuntimeException {

    private final ErrorType errorType;

    public GameException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
