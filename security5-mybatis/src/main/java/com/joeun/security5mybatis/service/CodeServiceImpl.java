package com.joeun.security5mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.security5mybatis.dto.Code;
import com.joeun.security5mybatis.mapper.CodeMapper;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Code> list() throws Exception {
        return codeMapper.list();
    }

    @Override
    public int insert(Code code) throws Exception {
        return codeMapper.insert(code);
    }

    @Override
    public Code select(String groupCode) throws Exception {
        return codeMapper.select(groupCode);
    }

    @Override
    public int update(Code code) throws Exception {
        return codeMapper.update(code);
    }

    @Override
    public int delete(String groupCode) throws Exception {
        return codeMapper.delete(groupCode);
    }

    @Override
    public List<Code> selectByGroupCode(String groupCode) throws Exception {
         return codeMapper.selectByGroupCode(groupCode);
    }
}
