package com.joeun.springresponse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;



/**
 * 🟢🟡🔴 @Controller
 * Spring MVC 의 HTTP 요청에 응답하는 컨트롤러 클래스로 지정하는 어노테이션
 * 🔵 컨트롤러 메소드로 주로 View 를 반환
 */
@Slf4j
@Controller
public class HomeController {

    /**
     * 🚀 void
     * - URL 과 동일한 경로의 뷰를 응답한다.
     */
    @GetMapping(value="/login")
    public void user() {
        log.info("[GET] - /login - 로그인 페이지");
    }
    
    /**
     * 🚀 String
     * - 문자열로 응답할 뷰의 경로를 지정한다.
     * @return
     */
    @GetMapping(value={"/", ""})
    public String home() {
        log.info("[GET] - / - 메인 페이지");
        return "index";
    }


    /**
     * 🟢 @ResponseBody
     * 🚀 String
     * - 문자열로 응답할 뷰의 경로를 지정한다.
     * @return
     */
    @ResponseBody
    @GetMapping(value="/hello")
    public String hello() {
        log.info("[GET] - /hello - 뷰가 아닌 문자열 본문을 응답합니다.");
        return "Hello Spring Boot~!";
    }
    
    
}
