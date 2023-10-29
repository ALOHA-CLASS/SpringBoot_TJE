package com.joeun.springsecurity5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joeun.springsecurity5.dto.Users;
import com.joeun.springsecurity5.mapper.UserMapper;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // Load user details from the database using MyBatis
       Users user = userMapper.login(username);

       if (user == null) {
           throw new UsernameNotFoundException("User not found with username: " + username);
       }

       // You may need to map your user roles/authorities to a collection of GrantedAuthority
       // For simplicity, using hardcoded role "USER" here
       return org.springframework.security.core.userdetails.User
               .withUsername(user.getUserId())
               .password(user.getUserPw())
               //.roles(user.getAuthList().stream().map((auth) -> auth.getAuth()).toArray(String[]::new))
               .roles("USER")
               .build();
    }
    
}
