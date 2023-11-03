package com.joeun.springresponse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StatusController {


    @GetMapping("/200")
    public ResponseEntity<String> ok() {
        log.info("[GET] - /200 - 서버가 클라이언트의 요청을 성공적으로 처리했을 때 응답하는 상태코드 입니다.");
        return ResponseEntity.ok("서버가 클라이언트의 요청을 성공적으로 처리했을 때 응답하는 상태코드 입니다.");
    }
    
    @GetMapping("/201")
    public ResponseEntity<String> created() {
        log.info("[GET] - /200 - 서버가 새로운 리소스를 성공적으로 생성 했을 때 응답하는 상태코드입니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body("서버가 새로운 리소스를 성공적으로 생성 했을 때 응답하는 상태코드입니다.");
    }

    @GetMapping("/400")
    public ResponseEntity<String> badRequest() {
        log.info("[GET] - /400 - 클라이언트 요청이 잘못됨을 알려주는 상태코드입니다.");
        return ResponseEntity.badRequest().body("클라이언트 요청이 잘못됨을 알려주는 상태코드입니다.");
    }
    
    
    @GetMapping("/403")
    public ResponseEntity<String> forbidden() {
        log.info("[GET] - /403 - 클라이언트가 자원 접근에 거부되었음을 알려주는 상태코드입니다. (주로 권한이 없을 때, 응답하는 상태코드입니다.)");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("클라이언트가 자원 접근에 거부되었음을 알려주는 상태코드입니다. (주로 권한이 없을 때, 응답하는 상태코드입니다.)");
    }
    
    
    @GetMapping("/404")
    public ResponseEntity<String> notFound() {
        log.info("[GET] - /404 - 클라이언트가 요청한 리소스가 서버에 없거나, 요청한 경로가 존재하지 않을 때 응답하는 상태코드 입니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클라이언트가 요청한 리소스가 서버에 없거나, 요청한 경로가 존재하지 않을 때 응답하는 상태코드 입니다.");
    }
    
    
    @GetMapping("/500")
    public ResponseEntity<String> internalServerError() {
        log.info("[GET] - /500 - 서버 측에서 에러나 예외가 발생했을 때 응답하는 상태코드 입니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 측에서 에러나 예외가 발생했을 때 응답하는 상태코드 입니다.");
    }
    
    
    

    
    
}
