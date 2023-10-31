package com.joeun.security5mybatis.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joeun.security5mybatis.dto.Users;

public interface UserService {
    // 회원 등록
    public int insert(Users user) throws Exception;

    // 회원 목록 조회
    public List<Users> list() throws Exception;

    // 회원 조회
    public Users select(int userNo) throws Exception;

    // 로그인
    public void login(Users user, HttpServletRequest requset) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 삭제
    public int delete(String userId) throws Exception;
    
}
