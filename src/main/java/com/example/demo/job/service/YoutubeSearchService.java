package com.example.demo.job.service;

import com.example.demo.job.dto.YouTubeSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class YoutubeSearchService {

    private final RestClient youtubeClient;

    public YouTubeSearchDto search(String query) {

        return youtubeClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", query)
                        .queryParam("part", "{part}")
                        .queryParam("type", "{type}")
                        .queryParam("maxResults", "{maxResults}")
                        .queryParam("key", "{key}")
                        .build()
                )
                .retrieve()
                .body(YouTubeSearchDto.class);
    }
}
