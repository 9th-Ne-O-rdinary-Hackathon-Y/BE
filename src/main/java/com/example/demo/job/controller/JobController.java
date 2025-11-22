package com.example.demo.job.controller;

import com.example.demo.global.response.ApiResponse;
import com.example.demo.job.dto.JobFindReqDto;
import com.example.demo.job.dto.JobResDto;
import com.example.demo.job.dto.JobFindRespDto;
import com.example.demo.job.service.JobFindService;
import com.example.demo.job.service.JobQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@Tag(name = "Job", description = "직무 관련 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/job")
public class JobController {
    private final JobQueryService jobQueryService;
    private final JobFindService jobFindService;

    @Operation(
        summary = "직무 찾기", 
        description = "3가지 게임 결과를 기반으로 사용자에게 적합한 직무를 추천합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = @ExampleObject(
                    name = "성공 응답",
                    value = """
                    {
                      "result": "SUCCESS",
                      "data": {
                        "description": "분석적으로 안정을 추구하며, 정확하게 처리하는 성향이에요.",
                        "job": [
                          {
                            "priority": 1,
                            "jobId": 1,
                            "jobName": "기획/PM",
                            "keywords": ["전략", "기획", "분석"],
                            "img": "pm.jpg",
                            "jobSummary": "PM 직무 설명"
                          },
                          {
                            "priority": 2,
                            "jobId": 4,
                            "jobName": "개발",
                            "keywords": ["개발", "코딩", "프로그래밍"],
                            "img": "dev.jpg",
                            "jobSummary": "개발 직무 설명"
                          }
                        ],
                        "personality": {
                          "riskTaking": "안정 추구",
                          "pace": 75.5,
                          "accuracy": 82.3,
                          "workStyle": "협업"
                        }
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "잘못된 요청",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "에러 응답",
                    value = """
                    {
                      "result": "ERROR",
                      "error": {
                        "code": "INVALID_REQUEST",
                        "message": "요청 데이터가 올바르지 않습니다."
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
    @PostMapping
    public ApiResponse<JobFindRespDto.JobFindResponse> findJob(
        @Valid @RequestBody JobFindReqDto.JobFindRequestPost request
    ){
        return ApiResponse.success(jobFindService.getJobResponse(request));
    }

    @Operation(
        summary = "직무 상세 조회",
        description = "직무 ID로 해당 직무의 상세 정보를 조회합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class)
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "잘못된 요청 (유효하지 않은 jobId)",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                    {
                      "result": "ERROR",
                      "error": {
                        "code": "INVALID_JOB_ID",
                        "message": "존재하지 않는 직무입니다."
                      }
                    }
                    """
                )
            )
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "500",
            description = "서버 오류"
        )
    })
    @GetMapping("/detail")
    public ApiResponse<JobResDto.getJobDetailPage> getDetail(
            @Parameter(description = "직무 ID", required = true, example = "1")
            @RequestParam Long jobId
    ){
        return ApiResponse.success(jobQueryService.getJobDetail(jobId));
    }
}