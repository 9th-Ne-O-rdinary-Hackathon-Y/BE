package com.example.demo.job.service;

import com.example.demo.job.dto.YouTubeSearchDto;
import com.example.demo.job.entity.Job;
import com.example.demo.job.entity.Youtube;
import com.example.demo.job.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.job.converter.YoutubeConverter.toYoutubeList;

@Service
@RequiredArgsConstructor
public class YoutubeSaveService {

    private final YoutubeRepository youtubeRepository;

    @Transactional
    public List<Youtube> saveYoutubeList(YouTubeSearchDto dto, Job job) {
        // 1) DTO -> 엔티티 변환
        List<Youtube> youtubeList = toYoutubeList(dto, job);

        // 2) DB에 저장 + 저장된 엔티티 리스트 반환
        List<Youtube> saved = youtubeRepository.saveAll(youtubeList);

        // saved 안에 id 값까지 다 채워져 있음
        return saved;
    }
}

