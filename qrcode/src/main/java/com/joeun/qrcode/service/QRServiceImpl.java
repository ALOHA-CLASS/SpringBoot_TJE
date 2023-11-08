package com.joeun.qrcode.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.joeun.qrcode.dto.Files;
import com.joeun.qrcode.dto.QR;
import com.joeun.qrcode.mapper.QRMapper;
import com.joeun.qrcode.util.QRCodeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QRServiceImpl implements QRService {

    @Value("${upload.path}")            // application.properties 에 설정한 업로드 경로 속성명
    private String uploadPath;          // 업로드 경로

    @Autowired
    private QRMapper qrMapper;

    @Autowired
    private FileService fileService;

    @Override
    public int makeQR(QR qr) throws Exception {
        log.info("url : " + qr.getUrl());

        // QR 코드 생성
        byte[] qrCodeBytes = QRCodeUtil.makeQRCodeImage(qr.getUrl());

        String fileName = "QR_" + UUID.randomUUID() + ".png";  // 파일 이름
        File file = new File(uploadPath, fileName);             // 파일 경로, 파일 이름
        OutputStream out = new FileOutputStream(file);
        FileCopyUtils.copy(qrCodeBytes, out);  // QR 코드 이미지 출력(저장)     // byte[], OutputStream

        // QR 코드 파일 정보 등록
        String uploadedPath = uploadPath + "/" + fileName;
        Files f = new Files();
        f.setFilePath(uploadedPath);
        f.setFileName(fileName);
        f.setParentTable( qr.getParentTable() );
        f.setParentNo( qr.getParentNo() );
        f.setFileSize( qrCodeBytes.length );
        f.setFileCode( 10 );                // 10 : QR코드
        fileService.insert(f);              // 파일 테이블에 등록

        qr.setFilePath(uploadedPath);
        qr.setFileSize( qrCodeBytes.length );
        qr.setFileSize(10);
        qrMapper.insert(qr);                // QR 테이블에 등록

        return 0;
    }

    @Override
    public List<QR> list() throws Exception {
        List<QR> qrList = qrMapper.list();
        return qrList;
    }

    @Override
    public QR select(int qrNo) throws Exception {
        QR qr = qrMapper.select(qrNo);
        return qr;
    }

    @Override
    public int insert(QR qr) throws Exception {
        int result = qrMapper.insert(qr);
        return result;
    }
    
    @Override
    public int update(QR qr) throws Exception {
        int result = qrMapper.update(qr);
        return result;
    }
    
    @Override
    public int delete(int qrNo) throws Exception {
        int result = qrMapper.delete(qrNo);

        // [TODO] QR 이미지 파일 삭제 추가...
        return result;
    }

    @Override
    public List<QR> listByParent(QR qr) throws Exception {
        List<QR> qrList = qrMapper.listByParent(qr);
        return qrList;
    }

    @Override
    public int deleteByParent(QR qr) throws Exception {
        int result = qrMapper.deleteByParent(qr);
        return result;
    }
    
}
