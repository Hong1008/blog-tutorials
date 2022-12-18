package com.example.demo.jwt;

import org.springframework.security.core.context.SecurityContextHolder;

public class MemberJwtContextHolder {

    public static MemberJwtToken getMemberJwtToken() {
        return (MemberJwtToken) SecurityContextHolder.getContext().getAuthentication();
    }
}
