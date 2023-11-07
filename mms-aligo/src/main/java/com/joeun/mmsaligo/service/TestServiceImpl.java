package com.joeun.mmsaligo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SMSService smsService;

    @Override
    public int myService() throws Exception {
        MultiValueMap<String, String> map =  new LinkedMultiValueMap<>();
        // ✅ 필수 정보
        // - receiver       :   1) 01012341234
        //                      2) 01011112222,01033334444
        // - msg            : 문자 메시지 내용
        // - testmode_yn    : 테스트 모드 여부 (Y-테스트⭕, N-테스트❌)
        String receiver = "01012341234";
        String msg = "메시지 내용";
        String testmode_yn = "Y";
        map.add("receiver", receiver);
        map.add("msg", msg);
        map.add("testmode_yn", testmode_yn);

        Map<String, Object> resultMap = smsService.send(map);
        Object resultCode = resultMap.get("result_code");
        Integer result_code = Integer.valueOf( resultCode != null ? resultCode.toString() : "-1" );
        String message = (String) resultMap.get("message");

        if( result_code == 1 )
            log.info("문자 발송 성공");
        if( result_code == -1 )
            log.info("문자 발송 실패");

        return 0;
    }
    
}
