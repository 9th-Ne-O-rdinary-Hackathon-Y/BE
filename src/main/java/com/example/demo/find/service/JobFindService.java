package com.example.demo.find.service;

import com.example.demo.find.dto.JobFindRespDto;
import com.example.demo.find.dto.JobFindReqDto;

public interface JobFindService {
    JobFindRespDto.JobNumber getJobNumber(JobFindReqDto.JobFindRequestPost request);
}
