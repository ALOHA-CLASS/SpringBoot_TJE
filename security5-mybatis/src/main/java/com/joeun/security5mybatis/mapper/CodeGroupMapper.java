package com.joeun.security5mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.security5mybatis.dto.CodeGroup;

@Mapper
public interface CodeGroupMapper {
    
    // 코드 그룹 목록
    public List<CodeGroup> list() throws Exception;

    // 코드 그룹 등록
    public int insert(CodeGroup CodeGroup) throws Exception;
    
    // 코드 그룹 조회
    public CodeGroup select(String codeGroup) throws Exception;

    // 코드 그룹 수정
    public int update(CodeGroup CodeGroup) throws Exception;

    // 코드 그룹 삭제
    public int delete(String groupCode) throws Exception;

}
