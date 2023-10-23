package com.joeun.board.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.board.dto.Files;
import com.joeun.board.service.FileService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



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
        Files file = fileService.select(fileNo);

        if( file == null ) {
            // BAD_REQUEST : 400, 클라이언트의 요청이 잘못되었음을 알려주는 상태코드
            response.setStatus(response.SC_BAD_REQUEST);
            return;
        }

        String filePath = file.getFilePath();
        String fileName = file.getFileName();

        // 다운로드 응답을 위한 헤더 세팅
        // - ContentType            : application/octet-stream
        // - Content-Disposition    : attachment, filename="파일명.확장자"
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // 파일 다운로드
        // - 파일 입력
        File downloadFile = new File(filePath);
        FileInputStream fis = new FileInputStream(downloadFile);

        // - 파일 출력
        ServletOutputStream sos = response.getOutputStream();

        // 다운로드
        FileCopyUtils.copy(fis, sos);

        // byte[] buffer = new byte[1024];             // 1024bytes = 1KB 단위 버퍼
        // int data;
        // while( (data = fis.read(buffer)) != -1 ) {  // 1KB 씩 파일입력
        //     sos.write(buffer, 0, data);         // 1KB 씩 파일출력
        // }
        // fis.close();
        // sos.close();
    }


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
    
    
}
