package com.example.demo.service;

import com.example.demo.controller.dto.MemberLoginDto;
import com.example.demo.entity.Member;
import com.example.demo.global.exception.CoreException;
import com.example.demo.global.exception.GlobalErrorType;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public void getMember(Member member) {

    }
}
