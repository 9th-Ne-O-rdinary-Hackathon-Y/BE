package com.example.demo.job.dto;

import lombok.Builder;
import java.util.List;

public class JobFindRespDto {
    
    @Builder
    public record JobFindResponse(
        String description,
        List<JobInfo> job,
        Personality personality
    ) {}
    
    @Builder
    public record JobInfo(
        Integer priority,
        Long jobId,  // Job ID 추가 (또는 Integer - Job 엔티티의 ID 타입에 맞춰서)
        String jobName,
        List<String> keywords,  // keyword1, keyword2, keyword3 대신 List로 변경
        String img,
        String jobSummary
    ) {}
    
    @Builder
    public record Personality(
        String riskTaking,
        Double pace,
        Double accuracy,
        String workStyle
    ) {}
}