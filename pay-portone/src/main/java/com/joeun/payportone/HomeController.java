package com.joeun.payportone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

    @GetMapping(value={"/" ,""})
    public String home() {
        return "index";
    }

    @GetMapping(value="/success")
    public String success(String result, String productId) {
        log.info("결제 성공!!!");
        log.info("result : " + result);
        log.info("productId : " + productId);
        return "success";
    }
    
    
}
