package com.joeun.security5mybatiscustomuser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.security5mybatiscustomuser.dto.UserAuth;
import com.joeun.security5mybatiscustomuser.dto.Users;

@Mapper
public interface UserMapper {

    // 회원 등록
    public int insert(Users user) throws Exception;
    
    // 회원 조회
    public Users select(int userNo) throws Exception;

    // 사용자 인증(로그인) - id
    public Users login(String username);
 
    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;
    
    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 삭제
    public int delete(String userId) throws Exception;
}
