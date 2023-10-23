package com.joeun.springsecurity.service;

import javax.servlet.http.HttpServletRequest;

import com.joeun.springsecurity.dto.Users;

public interface UserService {

    // 회원 등록
    public int insert(Users user) throws Exception;

    // 회원 조회
    public Users select(int userNo) throws Exception;

    // 로그인
    public void login(Users user, HttpServletRequest requset) throws Exception;
    
}
