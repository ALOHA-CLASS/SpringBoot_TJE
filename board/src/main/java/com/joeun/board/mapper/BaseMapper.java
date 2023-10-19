package com.joeun.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.board.dto.Base;
 
@Mapper
public interface BaseMapper {

    // 기본 목록
    public List<Base> list() throws Exception;
    // 기본 조회
    public Base select(int baseNo) throws Exception;
    // 기본 등록
    public int insert(Base base) throws Exception;
    // 기본 수정
    public int update(Base base) throws Exception;
    // 기본 삭제
    public int delete(int baseNo) throws Exception;
    
    
}












