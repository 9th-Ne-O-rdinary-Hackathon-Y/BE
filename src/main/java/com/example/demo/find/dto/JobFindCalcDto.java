package com.example.demo.find.dto;

import lombok.Builder;

public class JobFindCalcDto {

    public record GameResult1(double acc, double pace) {}

    @Builder
    public record AnalysisResultDto(
        double riskTaking,
        double accuracy,
        double reasoning,
        double workStyle,
        double stress,
        double pace
    ) {
        // 점수 합산을 편하게 하기 위한 헬퍼 메서드
        public AnalysisResultDto add(AnalysisResultDto other) {
            return new AnalysisResultDto(
                this.riskTaking + other.riskTaking,
                this.accuracy + other.accuracy,
                this.reasoning + other.reasoning,
                this.workStyle + other.workStyle,
                this.stress + other.stress,
                this.pace + other.pace
            );
        }
        
        // 초기 0점 객체 생성
        public static AnalysisResultDto zero() {
            return new AnalysisResultDto(0, 0, 0, 0, 0, 0);
        }
    }



    
}
