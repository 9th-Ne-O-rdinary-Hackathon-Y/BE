package com.example.demo.job.controller;

import com.example.demo.global.response.ApiResponse;
import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.service.JobQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/job")
public class JobController {
    private final JobQueryService jobQueryService;

    @GetMapping
    public ApiResponse<JobResDto.getJobDetailPage> getDetail(
            @RequestParam Long jobId
    ){

        return ApiResponse.success(jobQueryService.getJobDetail(jobId));
    }
}
