package com.example.demo.jwt;

import com.example.demo.authentication.SimpleJwtAuthenticationConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MemberJwtAuthenticationConverter extends SimpleJwtAuthenticationConverter {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt, Collection<GrantedAuthority> authorities) {
        return new MemberJwtToken(jwt, authorities);
    }
}
