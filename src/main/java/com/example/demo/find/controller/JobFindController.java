package com.example.demo.find.controller;

import com.example.demo.global.response.ApiResponse;
import com.example.demo.find.service.JobFindService;
import com.example.demo.find.dto.JobFindRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.find.dto.JobFindReqDto;
import jakarta.validation.Valid;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/find")
public class JobFindController {
    private JobFindService jobFindService;

    @PostMapping("/job")
    public ApiResponse<JobFindRespDto.JobNumber> findJob(
            @Valid @RequestBody JobFindReqDto.JobFindRequestPost request
    ){

        return ApiResponse.success(jobFindService.getJobNumber(request));
    }
}
