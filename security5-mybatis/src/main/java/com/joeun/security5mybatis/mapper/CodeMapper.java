package com.joeun.security5mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.security5mybatis.dto.Code;
import com.joeun.security5mybatis.dto.CodeGroup;

@Mapper
public interface CodeMapper {

    // 코드 목록
    public List<Code> list() throws Exception;
    
    // 코드 등록
    public int insert(Code code) throws Exception;
    
    // 코드 조회
    public Code select(String groupCode) throws Exception;
    
    // 코드 수정
    public int update(Code code) throws Exception;
    
    // 코드 삭제
    public int delete(String groupCode) throws Exception;

    // 코드 목록 - groupCode
    public List<Code> selectByGroupCode(String groupCode) throws Exception;
}
