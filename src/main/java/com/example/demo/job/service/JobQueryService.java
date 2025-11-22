package com.example.demo.job.service;

import com.example.demo.job.dto.JobResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobQueryService {

//    private final YouTubeRepository youTubeRepository;
//    private final BootcampRepository bootcampRepository;

    public JobResDto.JobDetailPage getJobDetail(Long jobId) {

        /*
        job = findbyid;

        JobResDto.job = converter.toYoutubeDetail(job)
        JobResDto.youtube = converter.toYoutubeDetail(job.youtube)
        JobResDto.bootcamp = converter.toBootCampDetail(job.bootcamp)

        return JobResDto.JobDetailPage = converter.toJobDetailPage(
             JobResDto.job, JobResDto.youtube, JobResDto.bootcamp
        )

        */
        return null;
    }
}
