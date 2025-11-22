package com.example.demo.controller;

import com.example.demo.controller.dto.MemberLoginDto;
import com.example.demo.entity.Member;
import com.example.demo.global.interceptor.CurrentUser;
import com.example.demo.global.response.ApiResponse;
import com.example.demo.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/me")
    public ApiResponse<?> getMember(@CurrentUser Member member) {
        // Implementation to get member details
        memberService.getMember(member);
        return ApiResponse.success(member.getEmail());
    }


}
