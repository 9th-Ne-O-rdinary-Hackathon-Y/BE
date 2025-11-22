package com.example.demo.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

import java.util.Map;

@Configuration
public class RestClientConfig {

    @Value("${youtube.api.key}")
    private String apiKey;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }


    @Bean
    public RestClient youtubeClient() {

        return RestClient.builder()
                .baseUrl("https://www.googleapis.com/youtube/v3")
                .defaultUriVariables(Map.of(
                        "part", "snippet",
                        "type", "video",
                        "maxResults", "10",
                        "key", apiKey
                ))
                .build();
    }
}

