package com.joeun.springsecurity5.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 여기에 사용자 정의 인증 로직을 구현합니다
        // 예시로 간단한 인증을 가정해 봅시다
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 데이터베이스나 외부 서비스 등을 통한 인증 로직을 추가합니다
        // 여기서 사용자명과 비밀번호를 확인할 수 있습니다

        // 예시로, 비어있지 않은 사용자명과 비밀번호는 모두 유효한 것으로 가정합니다
        if (!username.isEmpty() && !password.isEmpty()) {
            log.info("사용자명: " + username);
            log.info("비밀번호: " + password);
            // 여기서 UserDetails의 사용자 정의 구현을 생성하고 인증 객체를 반환할 수 있습니다
            // 여기서는 단순히 제공된 Authentication 인스턴스를 반환하여 성공적인 인증을 나타냅니다
            return authentication;
        } else {
            // 인증이 실패하면 AuthenticationException을 throw하거나 null을 반환할 수 있습니다
            // throw new CustomAuthenticationException("잘못된 자격 증명");
            return null;
        }
    }
}
