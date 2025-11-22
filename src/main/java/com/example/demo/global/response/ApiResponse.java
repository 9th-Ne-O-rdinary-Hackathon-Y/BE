package com.example.demo.global.response;

import com.example.demo.global.exception.ErrorMessage;
import com.example.demo.global.exception.ErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    @Schema(description = "응답 결과", example = "SUCCESS")
    ResultType result,
    
    @Schema(description = "응답 데이터")
    T data,
    
    @Schema(hidden = true)  // Swagger에서 숨김
    ErrorMessage error
) {

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }
    
    public static <S> ApiResponse<S> success(S data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(ErrorType error) {
        return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error));
    }
}