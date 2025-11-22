package com.example.demo.job.dto;

import lombok.Builder;

import java.util.List;

public class JobResDto {

    @Builder
    public record getJobDetailPage(
            getJob getJobDto,
            JobResDto.YoutubeListDTO youtubeListDto, //youtubeListDto
            JobResDto.BootcampListDTO bootcampListDTO
    ){}

    @Builder
    public record getJob(
            String name,
            List<String> keyword,
            String content,
            String image
    ){}


    @Builder
    public record YoutubeListDTO(
            List<getYoutube> getYoutubeList
    ){}

    @Builder
    public record getYoutube(
            Long youtubeId,
            String title,
            String URL,
            String image
    ){}

    @Builder
    public record BootcampListDTO(
            List<getBootcamp> getBootcampList
    ){}

    @Builder
    public record getBootcamp(
            Long bootcampId,
            String name,
            String URL,
            String image
    ){}
}
