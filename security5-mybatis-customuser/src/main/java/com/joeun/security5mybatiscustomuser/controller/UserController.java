package com.joeun.security5mybatiscustomuser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    
    /**
     * 사용자 페이지
     * @return
     */
    // 회원권한(ROLE_USER)을 가진 사용자만 접근 허용
    // @PreAuthorize("hasRole('ROLE_USER')")
    // @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    // @Secured("ROLE_USER")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value={"/", ""})
    public String index() {
        log.info("/user");
        // int result = 10 / 0;
        // log.info(result + "");
        return "user/index";
    }

    
    
    
}
