package com.example.demo.job.converter;

import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.entity.Bootcamp;
import com.example.demo.job.entity.Job;
import com.example.demo.job.entity.Youtube;

import java.util.List;

public class JobConverter {

    public static JobResDto.JobDetailPage toJobDetailPage(
            JobResDto.Job jobDto,
            JobResDto.YoutubeListDTO youtubeListDTO,
            JobResDto.BootcampListDTO bootcampListDTO) {

        return JobResDto.JobDetailPage.builder()
                .jobDto(jobDto)
                .youtubeListDto(youtubeListDTO)
                .bootcampListDTO(bootcampListDTO)
                .build();
    }

    public static JobResDto.Job toJobDetail(Job job){
        return JobResDto.Job.builder()
                .name(job.getName())
                .keyword(job.getKeyword())
                .content(job.getContent())
                .image(job.getImage())
                .build();
    }

    public static JobResDto.YoutubeListDTO toYoutubeListDTO(List<Youtube> youtubeList) {
        return JobResDto.YoutubeListDTO.builder()
                .youtubeList(
                        youtubeList.stream()
                                .map(JobConverter::toYoutubeDetail)
                                .toList()
                )
                .build();
    }

    public static JobResDto.BootcampListDTO toBootcampListDTO(List<Bootcamp> bootcampList) {
        return JobResDto.BootcampListDTO.builder()
                .bootcampList(
                        bootcampList.stream()
                                .map(JobConverter::toBootcampDetail)
                                .toList()
                )
                .build();
    }

    public static JobResDto.Youtube toYoutubeDetail(Youtube youtube){
        return JobResDto.Youtube.builder()
                .title(youtube.getTitle())
                .URL(youtube.getURL())
                .image(youtube.getImage())
                .build();
    }

    public static JobResDto.Bootcamp toBootcampDetail(Bootcamp bootcamp){
        return JobResDto.Bootcamp.builder()
                .name(bootcamp.getName())
                .brand(bootcamp.getBrand())
                .URL(bootcamp.getURL())
                .image(bootcamp.getImage())
                .build();
    }
}
