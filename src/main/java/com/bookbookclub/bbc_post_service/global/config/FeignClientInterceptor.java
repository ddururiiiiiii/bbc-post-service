package com.bookbookclub.bbc_post_service.global.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Feign 요청 시 JWT 토큰을 Authorization 헤더에 자동 추가하는 인터셉터
 */
@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.debug("[Feign] 인증 정보 없음 - Authorization 미첨부");
            return;
        }

        Object credentials = authentication.getCredentials();
        if (credentials == null) {
            log.debug("[Feign] 인증 토큰 없음");
            return;
        }

        String token = credentials.toString();
        log.debug("[Feign] Authorization 헤더 추가됨");
        template.header("Authorization", "Bearer " + token);
    }
}