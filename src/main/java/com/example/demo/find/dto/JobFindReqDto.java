package com.example.demo.find.dto;

import lombok.Builder;

public class JobFindReqDto {
    
    @Builder
    public record JobFindRequestPost(
        Game1 game1,
        Game2 game2,
        Game3 game3
    ) {}
    
    @Builder
    public record Game1(
        Integer clientX,
        Integer clientY,
        Integer answerX,
        Integer answerY,
        Integer ms
    ) {}
    
    @Builder
    public record Game2(
        String question1,
        String question2,
        String question3,
        String question4,
        String question5
    ) {}
    
    @Builder
    public record Game3(
        Integer select
    ) {}
}