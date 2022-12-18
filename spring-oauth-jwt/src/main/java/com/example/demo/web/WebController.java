package com.example.demo.web;

import com.example.demo.jwt.JwtProvider;
import com.example.demo.jwt.MemberJwtContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
public class WebController {

    private final JwtProvider jwtProvider;

    public WebController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/auth/check")
    public Long check() {
        return MemberJwtContextHolder.getMemberJwtToken().getMemberId();
    }

    @GetMapping("/token")
    public String generateToken() throws Exception {
        return jwtProvider.generateAccessToken(Collections.singletonMap("member_id", "1"), Duration.ofMinutes(5));
    }
}
