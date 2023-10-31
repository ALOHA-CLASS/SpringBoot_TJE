package com.joeun.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Files;
import com.joeun.board.mapper.BoardMapper;
import com.joeun.board.mapper.FileMapper;

@Service
public class BoardServiceImpl implements BoardService {
    // quick fix : ctrl + .

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileMapper fileMapper;

    @Value("${upload.path}")            // application.properties 에 설정한 업로드 경로 속성명
    private String uploadPath;          // 업로드 경로

    @Override
    public List<Board> list() throws Exception {
        List<Board> boardList = boardMapper.list();
        return boardList;
    }

    @Override
    public Board select(int boardNo) throws Exception {
        Board board = boardMapper.select(boardNo);
        // 조회수 증가...
        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        String parentTable = "board";
        int parentNo = boardMapper.maxPk();

        // 파일 업로드 
        List<MultipartFile> fileList = board.getFile();

        if( !fileList.isEmpty() )
        for (MultipartFile file : fileList) {

            if( file.isEmpty() ) continue;

            // 파일 정보 : 원본파일명, 파일 용량, 파일 데이터 
            String originName = file.getOriginalFilename();
            long fileSize = file.getSize();
            byte[] fileData = file.getBytes();
            
            // 업로드 경로
            // 파일명 중복 방지 방법(정책)
            // - 날짜_파일명.확장자
            // - UID_파일명.확장자

            // UID_강아지.png
            String fileName = UUID.randomUUID().toString() + "_" + originName;

            // c:/upload/UID_강아지.png
            String filePath = uploadPath + "/" + fileName;

            // 파일업로드
            // - 서버 측, 파일 시스템에 파일 복사
            // - DB 에 파일 정보 등록
            File uploadFile = new File(uploadPath, fileName);
            FileCopyUtils.copy(fileData, uploadFile);       // 파일 업로드

            // FileOutputStream fos = new FileOutputStream(uploadFile);
            // fos.write(fileData);
            // fos.close();

            Files uploadedFile = new Files();
            uploadedFile.setParentTable(parentTable);
            uploadedFile.setParentNo(parentNo);
            uploadedFile.setFileName(fileName);
            uploadedFile.setFilePath(filePath);
            uploadedFile.setOriginName(originName);
            uploadedFile.setFileSize(fileSize);
            uploadedFile.setFileCode(0);

            fileMapper.insert(uploadedFile);
        }

        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result;
    }
    
    @Override
    public int delete(int boardNo) throws Exception {
        int result = boardMapper.delete(boardNo);
        return result;
    }
    
}
  