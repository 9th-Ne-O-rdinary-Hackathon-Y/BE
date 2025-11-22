package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberLoginDto {

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Request{
        private String email;
        private String password;
    }

    @NoArgsConstructor
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        private String token;

        public static Response of(String token){
            return Response.builder()
                    .token(token)
                    .build();
        }
    }
}
