package com.example.demo.job.service;

import com.example.demo.job.dto.JobFindRespDto;
import com.example.demo.job.dto.JobFindReqDto;

public interface JobFindService {
    JobFindRespDto.JobFindResponse getJobResponse(JobFindReqDto.JobFindRequestPost request);
}