package com.example.demo.job.converter;

import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.entity.Bootcamp;
import com.example.demo.job.entity.Job;
import com.example.demo.job.entity.Youtube;

import java.util.List;

public class JobConverter {

    public static JobResDto.getJobDetailPage toJobDetailPage(
            JobResDto.getJob getJobDto,
            JobResDto.YoutubeListDTO youtubeListDTO,
            JobResDto.BootcampListDTO bootcampListDTO) {

        return JobResDto.getJobDetailPage.builder()
                .getJobDto(getJobDto)
                .youtubeListDto(youtubeListDTO)
                .bootcampListDTO(bootcampListDTO)
                .build();
    }

    public static JobResDto.getJob toJobDetail(Job job){
        return JobResDto.getJob.builder()
                .name(job.getName())
                .keyword(job.getKeyword())
                .content(job.getContent())
                .image(job.getImage())
                .build();
    }

    public static JobResDto.YoutubeListDTO toYoutubeListDTO(List<Youtube> youtubeList) {
        return JobResDto.YoutubeListDTO.builder()
                .getYoutubeList(
                        youtubeList.stream()
                                .map(JobConverter::toYoutubeDetail)
                                .toList()
                )
                .build();
    }

    public static JobResDto.BootcampListDTO toBootcampListDTO(List<Bootcamp> bootcampList) {
        return JobResDto.BootcampListDTO.builder()
                .getBootcampList(
                        bootcampList.stream()
                                .map(JobConverter::toBootcampDetail)
                                .toList()
                )
                .build();
    }

    public static JobResDto.getYoutube toYoutubeDetail(Youtube youtube){
        return JobResDto.getYoutube.builder()
                .title(youtube.getTitle())
                .URL(youtube.getURL())
                .image(youtube.getImage())
                .build();
    }

    public static JobResDto.getBootcamp toBootcampDetail(Bootcamp bootcamp){
        return JobResDto.getBootcamp.builder()
                .name(bootcamp.getName())
                .brand(bootcamp.getBrand())
                .URL(bootcamp.getURL())
                .image(bootcamp.getImage())
                .build();
    }
}
