// Controller Docs
package com.example.demo.game.controller;

import com.example.demo.game.dto.GameDto;
import com.example.demo.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Game", description = "게임 관련 API")
public interface GameControllerDocs {

    @Operation(
        summary = "전체 게임 조회",
        description = "모든 게임 정보를 content_num 순으로 조회합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "성공 응답",
                    value = """
                    {
                      "result": "SUCCESS",
                      "data": {
                        "games": [
                          {
                            "id": 1,
                            "icon": "http://example.com/icon1.png",
                            "content": "정해진 규칙 내에서 일하기",
                            "contentNum": 1
                          },
                          {
                            "id": 2,
                            "icon": "http://example.com/icon2.png",
                            "content": "새로운 시도를 계속하기",
                            "contentNum": 1
                          },
                          {
                            "id": 3,
                            "icon": "http://example.com/icon3.png",
                            "content": "오타 없게 꼼꼼하게 작성하기",
                            "contentNum": 2
                          }
                        ]
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "500",
            description = "서버 오류",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "서버 에러 응답",
                    value = """
                    {
                      "result": "ERROR",
                      "error": {
                        "code": "INTERNAL_SERVER_ERROR",
                        "message": "서버 내부 오류가 발생했습니다."
                      }
                    }
                    """
                )
            )
        )
    })
    @GetMapping
    ApiResponse<GameDto.GameListResponse> getAllGames();
}