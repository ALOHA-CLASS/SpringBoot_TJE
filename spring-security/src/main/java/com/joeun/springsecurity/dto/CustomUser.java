package com.joeun.springsecurity.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

/**
 * User         : 스프링 시큐리티 사용자 정보 클래스
 * CustomUser   : User 자식 클래스
 * Users        : 프로젝트의 사용자 정보 클래스
 */
@Data
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
        
        // List<UserAuth> userAuthList = users.getAuthList();
        // List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        // for (int i = 0; i < userAuthList.size(); i++) {
        //     authList.add( new SimpleGrantedAuthority( userAuthList.get(i).getAuth() ));
        // }
        // super(users.getUserId(), users.getUserPw(), authList);
        
        this.users = users;
    }



    
}
