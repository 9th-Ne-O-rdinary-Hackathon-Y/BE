package com.example.demo.job.dto;

import lombok.Builder;

import java.util.List;

public class JobResDto {

    @Builder
    public record JobDetailPage(
            Job job,
            Youtube youtube,
            Bootcamp bootcamp
    ){}

    @Builder
    public record Job(
            String name,
            List<String> keyword,
            String content,
            String image
    ){}

    @Builder
    public record Youtube(
            String title,
            String URL,
            String image
    ){}

    @Builder
    public record Bootcamp(
            String name,
            String brand,
            String URL,
            String image
    ){}
}
