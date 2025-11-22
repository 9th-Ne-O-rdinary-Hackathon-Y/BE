package com.example.demo.job.controller;

import com.example.demo.global.response.ApiResponse;
import com.example.demo.job.dto.JobFindReqDto;
import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.dto.JobFindRespDto;
import com.example.demo.job.service.JobFindService;
import com.example.demo.job.service.JobQueryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@Tag(name = "Job", description = "직무 관련 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/job")
public class JobController {
    private final JobQueryService jobQueryService;
    private final JobFindService jobFindService;

    @PostMapping
    public ApiResponse<JobFindRespDto.JobFindResponse> findJob(
        @Valid @RequestBody JobFindReqDto.JobFindRequestPost request
    ){
        return ApiResponse.success(jobFindService.getJobResponse(request));
    }

    
    @GetMapping("/detail")
    public ApiResponse<JobResDto.getJobDetailPage> getDetail(
            @Parameter(description = "직무 ID", required = true, example = "1")
            @RequestParam Long jobId
    ){
        return ApiResponse.success(jobQueryService.getJobDetail(jobId));
    }
}