package com.joeun.security5mybatis.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {

    private Users users;         

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(Users users) {
        // this(), super() - 는 생성자 안에서 항상 첫번째 문장
        super(users.getUserId()
             ,users.getUserPw()
             ,users.getAuthList().stream()
                                 .map( (auth) -> new SimpleGrantedAuthority(auth.getAuth()))
                                 .collect(Collectors.toList()));
        
        this.users = users;
    }



    
}

