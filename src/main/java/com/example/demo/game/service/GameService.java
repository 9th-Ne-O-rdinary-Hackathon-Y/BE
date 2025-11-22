package com.example.demo.game.service;

import com.example.demo.game.dto.GameDto;
import com.example.demo.game.entity.Game;
import com.example.demo.game.exception.GameErrorType;
import com.example.demo.game.exception.GameException;
import com.example.demo.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;

    // 전체 게임 조회
    public GameDto.GameListResponse getAllGames() {
        List<Game> games = gameRepository.findAllByOrderByContentNumAsc();
        return GameDto.GameListResponse.from(games);
    }

    // ID로 단건 조회
    public GameDto.GameResponse getGameById(Long id) {
        Game game = gameRepository.findById(id)
            .orElseThrow(() -> new GameException(GameErrorType.GAME_NOT_FOUND));
        return GameDto.GameResponse.from(game);
    }

    // content_num으로 조회
    public GameDto.GameListResponse getGamesByContentNum(Integer contentNum) {
        List<Game> games = gameRepository.findByContentNum(contentNum);
        return GameDto.GameListResponse.from(games);
    }
}