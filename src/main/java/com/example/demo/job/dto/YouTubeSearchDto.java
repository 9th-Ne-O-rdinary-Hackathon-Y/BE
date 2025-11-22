package com.example.demo.job.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record YouTubeSearchDto(
        List<Item> items
) {
    public record Item(
            Id id,
            Snippet snippet
    ) {}

    public record Id(
            String videoId
    ) {}

    public record Snippet(
            String title,
            @JsonProperty("thumbnails") ThumbnailWrapper thumbnails
    ) {}

    public record ThumbnailWrapper(
            @JsonProperty("medium") Thumbnail medium
    ) {}

    public record Thumbnail(
            String url
    ) {}
}
