package com.joeun.qrcode.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.qrcode.dto.QR;
import com.joeun.qrcode.service.QRService;
import com.joeun.qrcode.util.MediaUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/qr")
public class QRController {

    @Autowired
    private QRService qrService;

    @GetMapping("/img")
    public ResponseEntity<byte[]> qrImg(int qrNo) throws Exception {
        log.info("[GET] - /file/qr");

        QR qr = qrService.select(qrNo);
        String filePath = qr.getFilePath();         // 파일 경로

        File f = new File(filePath);                // 파일 객체 생성
        String ext = filePath.substring( filePath.lastIndexOf(".") );  // 확장자
        
        byte[] bytes = FileCopyUtils.copyToByteArray(f);    // 파일객체에서 파일 데이터 추출

        // 이미지 파일을 읽어서 응답
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaUtil.getMediaType(ext));  // image/**
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    
}
