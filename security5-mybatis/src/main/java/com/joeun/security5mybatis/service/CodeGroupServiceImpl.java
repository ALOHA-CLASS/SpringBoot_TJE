package com.joeun.security5mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.security5mybatis.dto.CodeGroup;
import com.joeun.security5mybatis.mapper.CodeGroupMapper;

@Service
public class CodeGroupServiceImpl implements CodeGroupService {

    @Autowired
    private CodeGroupMapper codeGroupMapper;

    @Override
    public List<CodeGroup> list() throws Exception {
        return codeGroupMapper.list();
    }

    @Override
    public int insert(CodeGroup codeGroup) throws Exception {
        return codeGroupMapper.insert(codeGroup);
    }

    @Override
    public CodeGroup select(String groupCode) throws Exception {
        return codeGroupMapper.select(groupCode);
    }

    @Override
    public int update(CodeGroup codeGroup) throws Exception {
        return codeGroupMapper.update(codeGroup);
    }

    @Override
    public int delete(String groupCode) throws Exception {
        return codeGroupMapper.delete(groupCode);
    }
}
