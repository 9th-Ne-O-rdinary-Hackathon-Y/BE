package com.example.demo.job.service;

import com.example.demo.job.converter.JobConverter;
import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.dto.YouTubeSearchDto;
import com.example.demo.job.entity.Job;
import com.example.demo.job.entity.Youtube;
import com.example.demo.job.exception.JobErrorType;
import com.example.demo.job.exception.JobException;
import com.example.demo.job.repository.JobRepository;
import com.example.demo.job.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.job.converter.YoutubeConverter.toYoutubeList;

@Service
@RequiredArgsConstructor
public class JobQueryService {

    private final JobRepository jobRepository;
    private final YoutubeSaveService youtubeSaveService;
    private final YoutubeSearchService youtubeSearchService;
    private static final String postfix = " 직무설명";

    public JobResDto.getJobDetailPage getJobDetail(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(()->new JobException(JobErrorType.JOB_NOT_FOUND));

        YouTubeSearchDto dto = youtubeSearchService.search(job.getName()+postfix);

        List<Youtube> youtubeList = youtubeSaveService.saveYoutubeList(dto, job);

        JobResDto.getJob getJobDto = JobConverter.toJobDetail(job);
        JobResDto.YoutubeListDTO youtubeListDTO = JobConverter.toYoutubeListDTO(youtubeList);
        JobResDto.BootcampListDTO bootcampListDTO = JobConverter.toBootcampListDTO(job.getBootcampList());

        return JobConverter.toJobDetailPage(
                getJobDto, youtubeListDTO, bootcampListDTO
        );
    }
}
