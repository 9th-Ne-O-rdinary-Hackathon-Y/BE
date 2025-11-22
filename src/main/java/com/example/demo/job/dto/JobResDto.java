package com.example.demo.job.dto;

import lombok.Builder;

import java.util.List;

public class JobResDto {

    @Builder
    public record JobDetailPage(
            JobResDto.Job jobDto,
            JobResDto.YoutubeListDTO youtubeListDto, //youtubeListDto
            JobResDto.BootcampListDTO bootcampListDTO
    ){}

    @Builder
    public record Job(
            String name,
            List<String> keyword,
            String content,
            String image
    ){}


    @Builder
    public record YoutubeListDTO(
            List<Youtube> youtubeList
    ){}

    @Builder
    public record Youtube(
            String title,
            String URL,
            String image
    ){}

    @Builder
    public record BootcampListDTO(
            List<Bootcamp> bootcampList
    ){}

    @Builder
    public record Bootcamp(
            String name,
            String brand,
            String URL,
            String image
    ){}
}
