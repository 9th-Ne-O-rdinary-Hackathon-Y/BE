package com.example.demo.global.health;

import com.example.demo.global.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ApiResponse<String> healthCheck() {
        return ApiResponse.success("OK");
    }
}
