package com.example.demo.game.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="icon", nullable = false)
    private String icon;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="content_num", nullable = false)
    private Integer contentNum;
}