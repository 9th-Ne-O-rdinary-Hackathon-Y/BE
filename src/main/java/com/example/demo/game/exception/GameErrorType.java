package com.example.demo.game.exception;

import com.example.demo.global.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GameErrorType implements ErrorType {

    GAME_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 게임을 찾을 수 없습니다."),
    INVALID_CONTENT_NUM(HttpStatus.BAD_REQUEST, "유효하지 않은 content_num입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
