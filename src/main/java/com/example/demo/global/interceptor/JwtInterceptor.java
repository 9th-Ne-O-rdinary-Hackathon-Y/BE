package com.example.demo.global.interceptor;

import com.example.demo.entity.Member;
import com.example.demo.global.util.JwtUtil;
import com.example.demo.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        // 1. Authorization 헤더 확인
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 2. JWT 파싱
        String token = header.substring(7);
        try {
            String email = jwtUtil.extractEmail(token);

            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("유저 없음"));

            // 3. 다른 곳에서 꺼내 쓸 수 있도록 request scope에 저장
            request.setAttribute("member", member);

            return true;

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
