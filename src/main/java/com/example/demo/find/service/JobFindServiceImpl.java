package com.example.demo.find.service;

import com.example.demo.find.dto.JobFindReqDto;
import com.example.demo.find.dto.JobFindRespDto;
import com.example.demo.find.dto.JobFindCalcDto; // AnalysisResultDto 위치

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class JobFindServiceImpl implements JobFindService {

    // Game 2 점수표 (키: 질문 내용)
    private static final Map<String, JobFindCalcDto.AnalysisResultDto> GAME2_SCORE_MAP = new HashMap<>();
    
    // Game 3 점수표 (키: 선택지 번호 1, 2, 3)
    private static final Map<Integer, JobFindCalcDto.AnalysisResultDto> GAME3_SCORE_MAP = new HashMap<>();
    
    static {
        // Q1. 정해진 규정 vs 새로운 시도
        GAME2_SCORE_MAP.put("정해진 규정 내에서 일하기", new JobFindCalcDto.AnalysisResultDto(0, 20, 5, 10, 5, 5));
        GAME2_SCORE_MAP.put("새로운 시도 계속하기", new JobFindCalcDto.AnalysisResultDto(35, 0, 5, 10, 15, 0));

        // Q2. 꼼꼼하게 vs 빠르게
        GAME2_SCORE_MAP.put("오타 없게 꼼꼼하게", new JobFindCalcDto.AnalysisResultDto(0, 40, 0, 0, 25, 0));
        GAME2_SCORE_MAP.put("빠르게 넘기기", new JobFindCalcDto.AnalysisResultDto(25, 0, 0, 0, 0, 30));

        // Q3. 직감 vs 근거
        GAME2_SCORE_MAP.put("직감대로 선택", new JobFindCalcDto.AnalysisResultDto(30, 0, 0, 0, 0, 25));
        GAME2_SCORE_MAP.put("근거 찾아보기", new JobFindCalcDto.AnalysisResultDto(0, 25, 40, 0, 5, 0));

        // Q4. 혼자 vs 함께
        GAME2_SCORE_MAP.put("혼자 몰입", new JobFindCalcDto.AnalysisResultDto(0, 0, 15, 0, 0, 15));
        GAME2_SCORE_MAP.put("함께 논의", new JobFindCalcDto.AnalysisResultDto(0, 0, 15, 70, 15, 0));

        // Q5. 급한 일 vs 중요한 일
        GAME2_SCORE_MAP.put("급한 일 먼저", new JobFindCalcDto.AnalysisResultDto(10, 0, 0, 0, 30, 25));
        GAME2_SCORE_MAP.put("중요한 일 먼저", new JobFindCalcDto.AnalysisResultDto(0, 15, 20, 10, 5, 0));

        // 1. 장기적 중요 요청 (Risk:40, Reasoning:50, Stress:60)
        GAME3_SCORE_MAP.put(1, new JobFindCalcDto.AnalysisResultDto(40, 15, 50, 20, 60, 10));
        
        // 2. 고객 임팩트 (Risk:50, Reasoning:40)
        GAME3_SCORE_MAP.put(2, new JobFindCalcDto.AnalysisResultDto(50, 15, 40, 20, 30, 10));
        
        // 3. 잘하고 빠른 것 (Accuracy:70, Pace:80, Work:60)
        GAME3_SCORE_MAP.put(3, new JobFindCalcDto.AnalysisResultDto(10, 70, 10, 60, 10, 80));
    }
    
@Getter
@RequiredArgsConstructor
private enum JobWeight {
    PM("기획/PM", 1, 0.10, 0.05, 0.15, 0.30, 0.15, 0.25),
    OPERATIONS("운영", 2, 0.15, 0.35, 0.05, 0.10, 0.25, 0.10),
    MARKETING("마케팅", 3, 0.25, 0.05, 0.30, 0.10, 0.10, 0.20),
    DEV("개발", 4, 0.15, 0.25, 0.10, 0.30, 0.10, 0.10),
    DATA("데이터", 5, 0.10, 0.30, 0.10, 0.35, 0.05, 0.10),
    SALES("영업", 6, 0.25, 0.05, 0.15, 0.05, 0.35, 0.15),
    HR("인사/HR", 7, 0.05, 0.25, 0.05, 0.10, 0.20, 0.35),
    DESIGN("디자인", 8, 0.10, 0.25, 0.30, 0.10, 0.10, 0.15);

    private final String jobName;
    private final int jobNumber;
    private final double paceW;
    private final double accW;
    private final double riskW;
    private final double reasoningW;
    private final double stressW;
    private final double workStyleW;

    // 사용자의 점수와 가중치를 곱해 적합도 계산
    public double calculateMatchScore(JobFindCalcDto.AnalysisResultDto userScore) {
        return (userScore.pace() * this.paceW) +
               (userScore.accuracy() * this.accW) +
               (userScore.riskTaking() * this.riskW) +
               (userScore.reasoning() * this.reasoningW) +
               (userScore.stress() * this.stressW) +
               (userScore.workStyle() * this.workStyleW);
    }
}


    @Override
    public JobFindRespDto.JobNumber getJobNumber(JobFindReqDto.JobFindRequestPost request) {
        
        // 1. Game 1 결과 계산 (민첩성 분석)
        JobFindReqDto.Game1 game1 = request.game1();
        JobFindCalcDto.GameResult1 rawResult1 = getGame1Result(
            game1.clientX(),
            game1.clientY(),
            game1.answerX(),
            game1.answerY(),
            game1.ms()
        );
        JobFindCalcDto.AnalysisResultDto result1 = new JobFindCalcDto.AnalysisResultDto(
            0, rawResult1.acc(), 0, 0, 0, rawResult1.pace()
        );


        // 2. Game 2 결과 계산 (성향 분석)
        JobFindReqDto.Game2 game2 = request.game2();
        JobFindCalcDto.AnalysisResultDto result2 = getGame2Result(
            game2.question1(),
            game2.question2(),
            game2.question3(),
            game2.question4(),
            game2.question5()
        );

        // 3. Game 3 결과 (단순 선택)
        JobFindReqDto.Game3 game3 = request.game3();
        JobFindCalcDto.AnalysisResultDto result3 = getGame3Result(game3.select());

        // 최종 결과
        JobFindCalcDto.AnalysisResultDto userFinalScore = calcAverage(result1, result2, result3);

        JobWeight bestJob = findBestJob(userFinalScore);
        
        return JobFindRespDto.JobNumber.builder()
            .jobNumber(bestJob.getJobNumber())
            .build();

    }
    
    private JobWeight findBestJob(JobFindCalcDto.AnalysisResultDto userScore) {
        return Arrays.stream(JobWeight.values())
                .max(Comparator.comparingDouble(job -> job.calculateMatchScore(userScore)))
                .orElse(JobWeight.PM); // 기본값 (혹시 모를 오류 대비)
    }

    private JobFindCalcDto.AnalysisResultDto calcAverage(
        JobFindCalcDto.AnalysisResultDto g1, 
        JobFindCalcDto.AnalysisResultDto g2, 
        JobFindCalcDto.AnalysisResultDto g3
    ) {
        double avgRisk      = (g1.riskTaking() + g2.riskTaking() + g3.riskTaking()) / 3.0;
        double avgAcc       = (g1.accuracy()   + g2.accuracy()   + g3.accuracy())   / 3.0;
        double avgReasoning = (g1.reasoning()  + g2.reasoning()  + g3.reasoning())  / 3.0;
        double avgWorkStyle = (g1.workStyle()  + g2.workStyle()  + g3.workStyle())  / 3.0;
        double avgStress    = (g1.stress()     + g2.stress()     + g3.stress())     / 3.0;
        double avgPace      = (g1.pace()       + g2.pace()       + g3.pace())       / 3.0;

        return new JobFindCalcDto.AnalysisResultDto(
            avgRisk, avgAcc, avgReasoning, avgWorkStyle, avgStress, avgPace
        );
    }

    // 1번째 게임 로직
    private JobFindCalcDto.GameResult1 getGame1Result(
        Integer clientX, Integer clientY, Integer answerX, Integer answerY, Integer ms
    ) {
        int k1 = 8;
        int k2 = 10;

        double error = Math.sqrt(Math.pow(clientX - answerX, 2) + Math.pow(clientY - answerY, 2));
        double acc = Math.max(0, 100 - (error * k1));
        double pace = Math.min(100, Math.max(0, 100 - (double) ms / k2)); // int 나눗셈 주의

        return new JobFindCalcDto.GameResult1(acc, pace);
    }

    // 2번째 게임 로직
    private JobFindCalcDto.AnalysisResultDto getGame2Result(
        String q1, String q2, String q3, String q4, String q5
    ) {
        // q1: 정해진 규정 내에서 일하기
        // q2: 빠르게 넘기기
        // q3: 직감대로 선택
        // q4: 함께 논의
        // q5: 급한 일 먼저
        // 선택한 경우:

        //Risk Taking: 0 + 25 + 30 + 0 + 10 = 65
        //Accuracy: 20 + 0 + 0 + 0 + 0 = 20
        //Reasoning: 5 + 0 + 0 + 15 + 0 = 20
        //Work Style: 10 + 0 + 0 + 70 + 0 = 80
        //Stress: 5 + 0 + 0 + 15 + 30 = 50
        //Pace: 5 + 30 + 25 + 0 + 25 = 85


        // 답변 리스트
        List<String> answers = List.of(q1, q2, q3, q4, q5);

        // 합산 변수 초기화
        double totalRisk = 0;
        double totalAcc = 0;
        double totalReasoning = 0;
        double totalWorkStyle = 0;
        double totalStress = 0;
        double totalPace = 0;

        for (String answer : answers) {
            // Map에서 점수 가져오기
            JobFindCalcDto.AnalysisResultDto score = GAME2_SCORE_MAP.get(answer);
            
            if (score != null) {
                totalRisk += score.riskTaking();
                totalAcc += score.accuracy();
                totalReasoning += score.reasoning();
                totalWorkStyle += score.workStyle();
                totalStress += score.stress();
                totalPace += score.pace();
            }
        }

        // 최종 합산 결과 반환
        return new JobFindCalcDto.AnalysisResultDto(
            totalRisk, totalAcc, totalReasoning, totalWorkStyle, totalStress, totalPace
        );
    }

    // 3번째 게임 로직
    private JobFindCalcDto.AnalysisResultDto getGame3Result(Integer select) {
        // 선택지가 1,2,3 범위를 벗어날 경우 대비 (기본값 0점 or 예외처리)
        return GAME3_SCORE_MAP.getOrDefault(select, JobFindCalcDto.AnalysisResultDto.zero());
    }

    // 최종 계산
}