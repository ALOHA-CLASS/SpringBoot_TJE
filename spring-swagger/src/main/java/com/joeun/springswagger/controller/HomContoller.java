package com.joeun.springswagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomContoller {

    @GetMapping(value={"/", ""})
    public String home() {
        // ✅ swagger 기본 경로
        // /swagger-ui/index.html
        return "redirect:swagger-ui/index.html";
    }
    
}
