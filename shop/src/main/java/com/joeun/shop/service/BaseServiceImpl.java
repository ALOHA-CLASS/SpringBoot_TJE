package com.joeun.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.shop.dto.Base;
import com.joeun.shop.mapper.BaseMapper;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseMapper baseMapper;

    @Override
    public Base select(int baseNo) throws Exception {
        return baseMapper.select(baseNo);
    }

    @Override
    public int insert(Base user) throws Exception {
        int result = baseMapper.insert(user);
        return result;
    }

    @Override
    public int update(Base user) throws Exception {
        int result = baseMapper.update(user);
        return result;
    }

    @Override
    public int delete(int baseNo) throws Exception {
        int result = baseMapper.delete(baseNo);
        return result;
    }
    
}
