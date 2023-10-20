package com.joeun.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    
    /**
     * 사용자 페이지
     * @return
     */
    @GetMapping(value={"/", ""})
    public String index() {
        return "user/index";
    }

    
    
    
}
