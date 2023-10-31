package com.joeun.security5mybatis.service;

import com.joeun.security5mybatis.dto.UserAuth;

public interface UserAuthService {
    
    // 회원 권한 목록
    public UserAuth list() throws Exception;

    // 회원 권한 등록
    public int insert(UserAuth userAuth) throws Exception;
    
    // 회원 권한 조회
    public UserAuth select(int authNo) throws Exception;

    // 회원 권한 수정
    public int update(UserAuth userAuth) throws Exception;

    // 회원 권한 삭제
    public int delete(int authNo) throws Exception;

    // 회원 권한 삭제 - userId, auth
    public int deleteByUserIdAndAuth(UserAuth userAuth) throws Exception;


}
