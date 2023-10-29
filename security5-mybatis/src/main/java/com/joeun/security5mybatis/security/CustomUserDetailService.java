package com.joeun.security5mybatis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joeun.security5mybatis.dto.Users;
import com.joeun.security5mybatis.mapper.UserMapper;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // MyBatis를 사용하여 데이터베이스에서 사용자 세부 정보를 가져옵니다.
       Users user = userMapper.login(username);

       if (user == null) {
           throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
       }

       // 🟢🟡🔴 Spring Security 의 User 객체 사용
       // 사용자의 역할/권한을 GrantedAuthority 컬렉션에 매핑
       // 1️⃣ 기본 권한 "USER" 를 하드코딩
       // 2️⃣ 권한을 String[] 타입으로 roles() 메소드로 지정
       return User
                 .withUsername(user.getUserId())
                 .password(user.getUserPw())
                 .roles(user.getAuthList().stream().map((auth) -> auth.getAuth()).toArray(String[]::new))
                //  .roles("USER")
                 .build();
    }
}
