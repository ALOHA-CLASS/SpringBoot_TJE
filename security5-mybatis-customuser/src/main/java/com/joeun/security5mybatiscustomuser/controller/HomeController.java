package com.joeun.security5mybatiscustomuser.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.joeun.security5mybatiscustomuser.dto.Users;
import com.joeun.security5mybatiscustomuser.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * 메인 화면
     * @param model
     * @param principal
     * @return
     */
    @GetMapping(value={"", "/"})
    public String home(Model model, Principal principal) {
        // Principal : 현재 로그인한 사용자의 정보를 확인하는 인터페이스
        String loginId = principal != null ? principal.getName() : "guest";
        // String loginId = principal.getName();
        model.addAttribute("loginId", loginId);

        return "index";
    }
    


    /**
     * 로그인 화면
     * @return
     */
    @GetMapping(value="/login")
    public String login(@CookieValue(value = "remember-id", required = false) Cookie cookie, Model model) {
        // @CookieValue(value="쿠키명", required=필수여부)
        // - required=true (defalut)     : 쿠키를 필수로 가져옴 ➡ 쿠키가 없으면 에러 
        // - required=false              : 쿠키 필수 ❌ ➡ 쿠키가 없어도 에러❌ (null)
        String userId = "";
        boolean rememberId = false;
        if( cookie != null ) {
            log.info("CookieName : " + cookie.getName());
            log.info("CookiValue : " + cookie.getValue());
            userId = cookie.getValue();
            rememberId = true;
        }

        model.addAttribute("userId", userId);
        model.addAttribute("rememberId", rememberId);
        
        return "login";
    }

    /**
     * 회원 가입 화면
     * @param param
     * @return
     */
    @GetMapping(value="/join")
    public String join() {
        return "join";
    }

    /**
     * 회원 가입 처리
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping(value="/join")
    public String joinPro(Users user, HttpServletRequest request) throws Exception {
        int result = userService.insert(user);

        // 회원 가입 성공 시, 바로 로그인
        if( result > 0 ) {  
            userService.login(user, request);
        }

        return "redirect:/";
    }


    /**
     * 접근 거부 에러 페이지
     * @param param
     * @return
     */
    @GetMapping(value="/exception")
    public String error(Authentication auth, Model model) {
        log.info(auth.toString());
        log.info("test");
        model.addAttribute("msg", "인증 거부 : " + auth.toString());
        return "exception";
    }
    
    
    
    
}
