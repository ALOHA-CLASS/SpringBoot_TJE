package com.joeun.qrcode.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.qrcode.dto.Files;
import com.joeun.qrcode.service.FileService;
import com.joeun.qrcode.util.MediaUtil;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 파일 다운로드
     * @param fileNo
     * @param response
     * @throws Exception
     */
    @GetMapping(value="/{fileNo}")
    public void fileDownload( @PathVariable("fileNo") int fileNo
                             ,HttpServletResponse response  ) throws Exception {

        int result = fileService.download(fileNo, response);
        
        if( result == 0 ) {
             response.setStatus(response.SC_BAD_REQUEST);
        }
    }


    
    /**
     *  파일 삭제
     * @param file
     * @return
     * @throws Exception
     */
    @DeleteMapping("")
    // public ResponseEntity<String> deleteFile(@RequestBody Files file) throws Exception {
    public ResponseEntity<String> deleteFile(Files file) throws Exception {
        log.info("[DELETE] - /file");
        int fileNo = file.getFileNo();
        log.info("fileNo : " + fileNo);
        if( fileNo == 0 )
            return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);       

        int result = fileService.delete(fileNo);

        if( result == 0 )
            return new ResponseEntity<String>("FAIL", HttpStatus.OK);    
        
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }


    @GetMapping("/file/img")
    public ResponseEntity<byte[]> thumbnailImg(int fileNo) throws Exception {
        log.info("[GET] - /file/img");

        Files file = fileService.select(fileNo);
        String filePath = file.getFilePath();

        File f = new File(filePath);
        String ext = filePath.substring( filePath.lastIndexOf(".") );
        

        byte[] bytes = FileCopyUtils.copyToByteArray(f);

        // 이미지 파일을 읽어서 응답
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaUtil.getMediaType(ext));
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    
}
