package com.example.demo.job.dto;

import java.util.List;

public record YouTubeSearchDto (
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
            String title
    ) {}
}