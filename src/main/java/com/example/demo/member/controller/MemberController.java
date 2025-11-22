package com.example.demo.member.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.global.interceptor.CurrentUser;
import com.example.demo.global.response.ApiResponse;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/me")
//    public ApiResponse<?> getMember(@CurrentUser Member member) {
//        // Implementation to get member details
//        memberService.getMember(member);
//        return ApiResponse.success(member.getEmail());
//    }


}
