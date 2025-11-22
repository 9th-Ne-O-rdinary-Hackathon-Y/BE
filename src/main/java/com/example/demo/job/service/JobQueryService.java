package com.example.demo.job.service;

import com.example.demo.job.converter.JobConverter;
import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.entity.Job;
import com.example.demo.job.exception.JobErrorType;
import com.example.demo.job.exception.JobException;
import com.example.demo.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobQueryService {

    private final JobRepository jobRepository;

    public JobResDto.getJobDetailPage getJobDetail(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(()->new JobException(JobErrorType.JOB_NOT_FOUND));

        JobResDto.getJob getJobDto = JobConverter.toJobDetail(job);
        JobResDto.YoutubeListDTO youtubeListDTO = JobConverter.toYoutubeListDTO(job.getYoutubeList());
        JobResDto.BootcampListDTO bootcampListDTO = JobConverter.toBootcampListDTO(job.getBootcampList());

        return JobConverter.toJobDetailPage(
                getJobDto, youtubeListDTO, bootcampListDTO
        );
    }
}
