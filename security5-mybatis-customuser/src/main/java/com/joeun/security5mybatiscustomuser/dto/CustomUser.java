package com.joeun.security5mybatiscustomuser.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class CustomUser implements UserDetails {

    private Users users;         

    public CustomUser(Users users) {
        this.users = users;
    }

    /**
     * 🟢🟡🔴 권한 getter 메소드
     * ✅ UserDetails 를 CustomUser 로 구현하여, 
     *     Spring Security 의 User 대신 사용자 정의 인증 객체(CustomUser)를 사용한다면,
     *     권한은 'ROLE_' 붙여서 사용해야한다.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return users.getAuthList().stream()
                                  .map( (auth) -> new SimpleGrantedAuthority(auth.getAuth()))
                                  .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return users.getUserPw();
    }

    @Override
    public String getUsername() {
        return users.getUserId();
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
        return users.getEnabled() == 0 ? false : true;
    }



    
}

