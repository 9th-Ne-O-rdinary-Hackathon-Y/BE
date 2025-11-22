package com.example.demo.job.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="keyword", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> keyword;

    @Column(name="summary", nullable = false)
    private String summary;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="image", nullable = false)
    private String image;

    @OneToMany(mappedBy = "job")
    private List<Youtube> youtubeList = new ArrayList<>();

    @OneToMany(mappedBy = "job")
    private List<Bootcamp> bootcampList = new ArrayList<>();
}
