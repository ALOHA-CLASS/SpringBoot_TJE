package com.joeun.springsecurity.service;

import com.joeun.springsecurity.dto.Users;

public interface UserService {

    // 회원 등록
    public int insert(Users user) throws Exception;

    // 회원 조회
    public Users select(int userNo) throws Exception;
    
}
