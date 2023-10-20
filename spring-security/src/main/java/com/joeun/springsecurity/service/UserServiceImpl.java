package com.joeun.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joeun.springsecurity.dto.UserAuth;
import com.joeun.springsecurity.dto.Users;
import com.joeun.springsecurity.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int insert(Users user) throws Exception {
        // 비밀번호 암호화
        String userPw = user.getUserPw();
        String encodedPw = passwordEncoder.encode(userPw);
        user.setUserPw(encodedPw);
        // 회원 등록
        int result = userMapper.insert(user);

        // 권한 등록
        if( result > 0 ) {
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId( user.getUserId() );
            userAuth.setAuth("ROLE_USER");          // 기본 권한 : 사용자 권한 (ROLE_USER)
            result = userMapper.insertAuth(userAuth);
        }

        return result;
    }

    @Override
    public Users select(int userNo) throws Exception {
        return userMapper.select(userNo);
    }
    
}
