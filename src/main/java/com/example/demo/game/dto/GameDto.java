// 2. DTO
package com.example.demo.game.dto;

import com.example.demo.game.entity.Game;
import lombok.Builder;

import java.util.List;

public class GameDto {

    // 게임 응답 DTO
    @Builder
    public record GameResponse(
        Long id,
        String icon,
        String content,
        Integer contentNum
    ) {
        public static GameResponse from(Game game) {
            return GameResponse.builder()
                .id(game.getId())
                .icon(game.getIcon())
                .content(game.getContent())
                .contentNum(game.getContentNum())
                .build();
        }
    }

    // 게임 리스트 응답 DTO
    @Builder
    public record GameListResponse(
        List<GameResponse> games
    ) {
        public static GameListResponse from(List<Game> games) {
            List<GameResponse> gameResponses = games.stream()
                .map(GameResponse::from)
                .toList();
            return new GameListResponse(gameResponses);
        }
    }
}