package com.joeun.security5mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.security5mybatis.dto.UserAuth;
import com.joeun.security5mybatis.mapper.UserAuthMapper;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    public UserAuth list() throws Exception {
        return userAuthMapper.list();
    }

    @Override
    public int insert(UserAuth userAuth) throws Exception {
        return userAuthMapper.insert(userAuth);
    }

    @Override
    public UserAuth select(int authNo) throws Exception {
        return userAuthMapper.select(authNo);
    }

    @Override
    public int update(UserAuth userAuth) throws Exception {
        return userAuthMapper.update(userAuth);
    }

    @Override
    public int delete(int authNo) throws Exception {
        return userAuthMapper.delete(authNo);
    }

    @Override
    public int deleteByUserIdAndAuth(UserAuth userAuth) throws Exception {
        return userAuthMapper.deleteByUserIdAndAuth(userAuth);
    }
}
