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
        String jobName,
        String keyword1,
        String keyword2,
        String keyword3,
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