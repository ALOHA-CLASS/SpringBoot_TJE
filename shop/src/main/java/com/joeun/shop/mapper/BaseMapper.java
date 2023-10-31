package com.joeun.shop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.shop.dto.Base;

@Mapper
public interface BaseMapper {

    // 기본 등록
    public int insert(Base Base) throws Exception;
    
    // 기본 조회
    public Base select(int BaseNo) throws Exception;

    // 기본 수정
    public int update(Base Base) throws Exception;

    // 기본 삭제
    public int delete(int baseNo) throws Exception;

}
