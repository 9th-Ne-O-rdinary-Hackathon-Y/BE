package com.example.demo.find.dto;

import lombok.Builder;

public class JobFindRespDto {
    
    @Builder
    public record JobNumber(
            int jobNumber
    ){}
}
