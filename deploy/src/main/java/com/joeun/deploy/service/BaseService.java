package com.joeun.deploy.service;

import java.util.List;

import com.joeun.deploy.dto.Base;

public interface BaseService {

    // 기본 목록
    public List<Base> list() throws Exception;
    // 기본 조회
    public Base select(int boardNo) throws Exception;
    // 기본 등록
    public int insert(Base board) throws Exception;
    // 기본 수정
    public int update(Base board) throws Exception;
    // 기본 삭제
    public int delete(int boardNo) throws Exception;
    
}
 