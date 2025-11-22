package com.example.demo.game.controller;

import com.example.demo.game.dto.GameDto;
import com.example.demo.game.service.GameService;
import com.example.demo.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController implements GameControllerDocs {

    private final GameService gameService;

    @Override
    public ApiResponse<GameDto.GameListResponse> getAllGames() {
        return ApiResponse.success(gameService.getAllGames());
    }
}