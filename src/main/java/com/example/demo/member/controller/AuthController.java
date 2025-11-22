package com.example.demo.member.controller;

import com.example.demo.member.controller.dto.MemberLoginDto;
import com.example.demo.global.response.ApiResponse;

import com.example.demo.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/signup")
//    public ApiResponse<MemberLoginDto.Response> registerMember(@RequestBody MemberLoginDto.Request request) {
//        String token = authService.registerMember(request.getEmail(), request.getPassword());
//        MemberLoginDto.Response response = MemberLoginDto.Response.of(token);
//        return ApiResponse.success(response);
//    }
//
//    @PostMapping("/login")
//    public ApiResponse<MemberLoginDto.Response> loginMember(@RequestBody MemberLoginDto.Request request) {
//        String token = authService.loginMember(request.getEmail(), request.getPassword());
//        MemberLoginDto.Response response = MemberLoginDto.Response.of(token);
//        return ApiResponse.success(response);
//    }
}
