package com.joeun.mmsaligo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joeun.mmsaligo.service.SMSService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
// @RestController
@Controller
public class SMSController {

    @Autowired
    private SMSService smsService;

    /**
     * 문자 보내기 화면
     * @return
     */
    @GetMapping(value="/send")
    public String send() {
        return "index";
    }
    

    /**
     * param
     *  - msg       : 문자메시지
     *  - receiver  : 받는번호1,받는번호2, ...      
     *              ex) 01011112222,01033334444
     * @param param
     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public String sendSMS(@RequestParam MultiValueMap<String, String> param) {
        log.info("msg : " + param.getFirst("msg"));  
        log.info("receiver : " + param.get("receiver").toString());
        log.info("rdate : " + param.getFirst("rdate"));  
        log.info("rtime : " + param.getFirst("rtime"));  
        log.info("testmode_yn : " + param.getFirst("testmode_yn"));  

        // 문자 전송 요청
        Map<String, Object> resultMap = smsService.send(param);
        
        Object resultCode = resultMap.get("result_code");
        Integer result_code = Integer.valueOf( resultCode != null ? resultCode.toString() : "-1" );
        String message = (String) resultMap.get("message");

        // ❌ 전송 실패
        if( result_code == -101 ) {
            log.info("(전송 실패) : " + message);
            return message;
        }
        // ⭕ 전송 성공
        return resultMap.toString();
    }
}

