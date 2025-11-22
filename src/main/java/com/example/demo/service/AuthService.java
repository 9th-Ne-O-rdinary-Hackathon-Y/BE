package com.example.demo.service;

import com.example.demo.controller.dto.MemberLoginDto;
import com.example.demo.entity.Member;
import com.example.demo.global.exception.CoreException;
import com.example.demo.global.exception.GlobalErrorType;
import com.example.demo.global.util.JwtUtil;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public String registerMember(String email, String password) {

        memberRepository.findByEmail(email).ifPresent(m -> {
            throw new CoreException(GlobalErrorType.DUPLICATED_EMAIL);
        });

        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        memberRepository.save(member);

        return jwtUtil.createToken(member.getEmail());
    }

    @Transactional
    public String loginMember(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CoreException(GlobalErrorType.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CoreException(GlobalErrorType.INCORRECT_PASSWORD);
        }

        return jwtUtil.createToken(member.getEmail());

    }
}
