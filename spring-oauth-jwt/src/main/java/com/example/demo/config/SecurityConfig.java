package com.example.demo.config;

import com.example.demo.authentication.CustomAccessDeniedHandler;
import com.example.demo.authentication.CustomEntryPoint;
import com.example.demo.authentication.SimpleJwtAuthenticationConverter;
import com.example.demo.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Configuration
public class SecurityConfig {

    @Value("${demo.secretKey}")
    private String secretKey;

    @Bean
    public SecurityFilterChain jwtChain(HttpSecurity http,
                                        SimpleJwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/auth/**").hasAuthority("SCOPE_acc")
                .anyRequest().permitAll()
                .and()
                .oauth2ResourceServer()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomEntryPoint())
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        MacAlgorithm algorithm = MacAlgorithm.HS256;

        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), algorithm.getName()))
                .macAlgorithm(algorithm)
                .build();
    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);
    }
}
