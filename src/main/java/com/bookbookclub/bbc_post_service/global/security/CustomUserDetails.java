package com.bookbookclub.bbc_post_service.global.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * JWT에서 인증된 사용자 정보를 담는 UserDetails 구현체
 * MSA 환경에서는 User 엔티티 대신 필요한 정보만 필드로 보관
 */
@Getter
public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String email;
    private final String nickname;
    private final String profileImageUrl;
    private final String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 현재 권한은 사용하지 않음
    }

    @Override
    public String getPassword() {
        return null; // OAuth2 + JWT 기반 인증이므로 비밀번호 없음
    }

    @Override
    public String getUsername() {
        return email; // 사용자 고유 식별자로 email 사용
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CustomUserDetails(Long id, String email, String nickname, String profileImageUrl, String role) {
        this.userId = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }
}