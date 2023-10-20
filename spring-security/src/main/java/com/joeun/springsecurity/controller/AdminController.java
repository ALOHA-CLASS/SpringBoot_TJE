package com.joeun.springsecurity.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    // /admin/, /admin
    @GetMapping(value={"/", ""})
    public String index() {
        log.info("[GET] - /admin");
        return "admin/index";
    }

    
}
