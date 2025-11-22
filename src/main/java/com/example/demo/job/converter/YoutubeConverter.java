package com.example.demo.job.converter;

import com.example.demo.job.dto.YouTubeSearchDto;
import com.example.demo.job.entity.Job;
import com.example.demo.job.entity.Youtube;

import java.util.List;

public class YoutubeConverter {

    public static Youtube toYoutubeEntity(YouTubeSearchDto.Item item, Job job) {

        String videoId = item.id().videoId(); // 유튜브 고유 id
        String title = item.snippet().title(); // 제목
        String thumbnailUrl = item.snippet().thumbnails().medium().url(); // 썸네일 URL

        return Youtube.builder()
                .title(title)
                .url("https://www.youtube.com/watch?v=" + videoId)  // url 컬럼
                .image(thumbnailUrl)                               // image 컬럼
                .job(job)
                .build();
    }

    public static List<Youtube> toYoutubeList(YouTubeSearchDto dto, Job job){
        return dto.items().stream()
                .map(item -> toYoutubeEntity(item, job))
                .toList();
    }
}
