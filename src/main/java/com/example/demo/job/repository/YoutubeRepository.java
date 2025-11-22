package com.example.demo.job.repository;

import com.example.demo.job.entity.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YoutubeRepository extends JpaRepository<Youtube, Long> {

}
